package org.example.ide2markingapi.feature.transaction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransactionCreateRequest(
        @NotBlank(message = "Transfer Account no is required")
        String ownerActNo,
        @NotBlank(message = "Transfer Account no of owner is required")
        String transferReceiverActNo,
        @NotNull(message = "Account is required")
        @Positive
        BigDecimal amount,
        String remark
) {
}
