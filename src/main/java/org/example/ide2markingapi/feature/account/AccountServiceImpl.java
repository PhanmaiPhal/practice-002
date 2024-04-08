package org.example.ide2markingapi.feature.account;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ide2markingapi.domain.Account;
import org.example.ide2markingapi.domain.AccountType;
import org.example.ide2markingapi.domain.User;
import org.example.ide2markingapi.domain.UserAccount;
import org.example.ide2markingapi.feature.account.dto.AccountCreateRequest;
import org.example.ide2markingapi.feature.account.dto.AccountRenameRequest;
import org.example.ide2markingapi.feature.account.dto.AccountResponse;
import org.example.ide2markingapi.feature.account.dto.AccountTransferLimitRequest;
import org.example.ide2markingapi.feature.accountType.AccountTypeRepository;
import org.example.ide2markingapi.feature.users.UserRepository;
import org.example.ide2markingapi.init.RandomUtil;
import org.example.ide2markingapi.mapper.AccountMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountTypeRepository accountTypeRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;
    private final UserAccountRepository userAccountRepository;

    @Override
    public void createNew(AccountCreateRequest accountCreateRequest) {
        // check account type
        AccountType accountType = accountTypeRepository.findByAlias(accountCreateRequest.accountTypeAlias())
                .orElseThrow(
                        () ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        "Invalid account type")

                );

        User user = userRepository.findByUuid(accountCreateRequest.userUuid())
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Invalid account type"));

        // map account dto to account entity
        Account account = accountMapper.fromAccountCreateRequest(accountCreateRequest);
        account.setAccountType(accountType);
        account.setActName(user.getName());
        account.setActNo(RandomUtil.generate9Digits());
        account.setTransferLimit(BigDecimal.valueOf(5000));
        account.setIsHidden(false);

        UserAccount userAccount = new UserAccount();
        userAccount.setAccount(account);
        userAccount.setUser(user);
        userAccount.setIsDeleted(false);
        userAccount.setIsBlocked(false);
        userAccount.setCreateAt(LocalDateTime.now());
        userAccountRepository.save(userAccount);
    }

    @Override
    public AccountResponse findAccountByActNumber(String accountNumber) {
        Account account = accountRepository.findAccountByActNo(accountNumber).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account has not been found!"
                )
        );
        return  accountMapper.toAccountResponse(account);
    }

    @Override
    public Page<AccountResponse> findAllAccount(int page , int size) {
        if (page < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Page number must be greater than or equal to zero");
        }

        if (size < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Size must be greater than or equal to one");
        }

        Sort sortByActName = Sort.by(Sort.Direction.ASC, "actName");
        PageRequest pageRequest = PageRequest.of(page, size, sortByActName);

        Page<Account> accounts = accountRepository.findAll(pageRequest);

        return accounts.map(accountMapper::toAccountResponse);
    }



    @Override
    public AccountResponse renameByActNo(String actNo, AccountRenameRequest request) {
        Account account = accountRepository.findAccountByActNo(actNo).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account has not been found!"
                )
        );
        if( account.getAlise() != null && account.getAlise().equals(request.actNewName())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "New name must not the same as old name"
            );
        }
        account.setAlise(request.actNewName());
        account = accountRepository.save(account);
        return accountMapper.toAccountResponse(account);
    }

    @Override
    @Transactional
    public void hideAccountByActNo(String actNo) {
        if (!accountRepository.existsByActNo(actNo)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Account has not been found"
            );
        }

        try {
            accountRepository.hiddenAccountActNo(actNo);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Something went wrong, please contact Developer"
            );
        }

    }

    @Override
    public AccountResponse updateTransferLimit(String actNo, AccountTransferLimitRequest accountTransferLimitRequest) {
        Account account = accountRepository.findAccountByActNo(actNo).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account has not been found!"
                )
        );
        account.setTransferLimit(accountTransferLimitRequest.transferLimit());
        log.info("{}", account.getTransferLimit());
        account = accountRepository.save(account);
        return accountMapper.toAccountResponse(account);
    }

}

