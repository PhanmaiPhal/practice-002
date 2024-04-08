package org.example.ide2markingapi.feature.transaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ide2markingapi.domain.Account;
import org.example.ide2markingapi.domain.Transaction;
import org.example.ide2markingapi.feature.account.AccountRepository;
import org.example.ide2markingapi.feature.transaction.dto.TransactionCreateRequest;
import org.example.ide2markingapi.feature.transaction.dto.TransactionResponse;
import org.example.ide2markingapi.mapper.TransactionMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;


    @Override
    public TransactionResponse transfer(TransactionCreateRequest transactionCreateRequest) {
        log.info("transfer(TransactionCreateRequest transactionCreateRequest): {}" ,transactionCreateRequest);

        // validate owner account no
        Account owner = accountRepository.findByActNo(transactionCreateRequest.ownerActNo())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account owner has not been found"
                ));

        //validate
        Account transferReceiver = accountRepository.findByActNo(transactionCreateRequest.transferReceiverActNo())
                .orElseThrow(()-> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                        "Account owner has not been found"
                ));

        //check amount transfer (balance <account) (act owner only)
        if(owner.getBalance().doubleValue() < transactionCreateRequest.amount().doubleValue()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Transaction has been the transfer limit");
        }
        // ដកលុយចេញពីគណនី
        owner.setBalance(owner.getBalance().subtract(transactionCreateRequest.amount()));

        // បញ្ចូលលុយទៅគណនី
        transferReceiver.setBalance(transferReceiver.getBalance().add(transactionCreateRequest.amount()));

        Transaction transaction = transactionMapper.fromTransactionCreateRequest(transactionCreateRequest);
        transaction.setOwner(owner);
        transaction.setTransferReceiver(transferReceiver);
        transaction.setTransactionType("TRANSFER");
        transaction.setTransactionAt(LocalDateTime.now());
        transaction.setStatus(true);
        transaction = transactionRepository.save(transaction);

        return transactionMapper.toTransactionResponse(transaction);
    }

    @Override
    public List<TransactionResponse> getTransactions(int page, int size, String sortDirection) {
        if (page < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Page number must be greater than or equal to zero");
        }

        if (size < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Size must be greater than or equal to one");
        }
        Sort.Direction direction = Sort.Direction.DESC;
        if(sortDirection.equalsIgnoreCase("ASC")){
            direction = Sort.Direction.ASC;
        }
        Sort sortByTransactionAt = Sort.by(direction, "transactionAt");
        PageRequest pageRequest = PageRequest.of(page, size, sortByTransactionAt );
        Page<Transaction> transactions = transactionRepository.findAll(pageRequest);

        return (List<TransactionResponse>) transactions.map(transactionMapper::toTransactionResponse);
    }

}
