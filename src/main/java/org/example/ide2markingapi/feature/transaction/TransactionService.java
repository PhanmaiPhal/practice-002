package org.example.ide2markingapi.feature.transaction;

import org.example.ide2markingapi.feature.transaction.dto.TransactionCreateRequest;
import org.example.ide2markingapi.feature.transaction.dto.TransactionResponse;

import java.util.List;

public interface TransactionService {
    TransactionResponse transfer(TransactionCreateRequest transactionCreateRequest);

    List<TransactionResponse> getTransactions(int page, int size,String sortDirection);
}
