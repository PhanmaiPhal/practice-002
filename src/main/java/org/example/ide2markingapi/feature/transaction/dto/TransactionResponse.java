package org.example.ide2markingapi.feature.transaction.dto;

import org.example.ide2markingapi.feature.account.dto.AccountSnippetResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(

    AccountSnippetResponse owner,

    AccountSnippetResponse transferReceiver,

    BigDecimal amount,

    String remark,

    String transactionType,

    Boolean status,

    LocalDateTime transactionAt

) {
}
