package org.example.ide2markingapi.feature.account.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;

public record AccountCreateRequest(
        @NotBlank(message = "Alias is required")
        String alias,

        @NotNull(message = "First balance is required (5$ up)")
        BigDecimal balance,

        @NotBlank(message = "Account type alias is required")
        String accountTypeAlias,

        @NotBlank(message = "User owner is required")
        String userUuid,

        //If user create account type card
        String cardNumber
) {
}
