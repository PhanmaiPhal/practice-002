package org.example.ide2markingapi.feature.transaction;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ide2markingapi.domain.Transaction;
import org.example.ide2markingapi.feature.transaction.dto.TransactionCreateRequest;
import org.example.ide2markingapi.feature.transaction.dto.TransactionResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    TransactionResponse transfer(@Valid @RequestBody TransactionCreateRequest transactionCreateRequest){
        return transactionService.transfer(transactionCreateRequest);
    }

    @GetMapping
    List<TransactionResponse> getTransactions(@RequestParam(defaultValue = "1") int page,
                                              @RequestParam(defaultValue = "10") int size,
                                              @RequestParam(required = false) String sortDirection) {
        return transactionService.getTransactions(page, size, sortDirection);
    }

}
