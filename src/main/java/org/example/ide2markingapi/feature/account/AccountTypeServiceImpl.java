package org.example.ide2markingapi.feature.account;

import lombok.RequiredArgsConstructor;
import org.example.ide2markingapi.domain.AccountType;
import org.example.ide2markingapi.feature.account.dto.AccountTypeResponse;
import org.example.ide2markingapi.mapper.AccountTypeMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@RequiredArgsConstructor

public class AccountTypeServiceImpl implements AccountTypeService{
    private final AccountTypeRepository accountTypeRepository;
    private final AccountTypeMapper accountTypeMapper;

    @Override
    public List<AccountTypeResponse> findAll() {

        List<AccountType> accountTypes = accountTypeRepository.findAll();

        return accountTypeMapper.toListAccountTypeResponse(accountTypes);
    }

    @Override
    public AccountTypeResponse findByAlias(String alias) {

        AccountType accountType = accountTypeRepository.findByAliasIgnoreCase(alias)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "account type does not exist!"
                ));

        return accountTypeMapper.toAccountTypeResponse(accountType);
    }

}
