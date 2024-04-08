package org.example.ide2markingapi.mapper;

import org.example.ide2markingapi.domain.Transaction;
import org.example.ide2markingapi.feature.transaction.dto.TransactionCreateRequest;
import org.example.ide2markingapi.feature.transaction.dto.TransactionResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
public interface TransactionMapper {
    TransactionResponse toTransactionResponse(Transaction transaction);

    Transaction fromTransactionCreateRequest(TransactionCreateRequest transactionCreateRequest);
    List<TransactionResponse> toTransactionResponse(List<Transaction> transactions);
}
