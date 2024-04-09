package org.example.ide2markingapi.feature.account.dto;

import org.example.ide2markingapi.feature.accountType.dto.AccountTypeResponse;
import org.example.ide2markingapi.feature.users.dto.UserResponse;

import java.math.BigDecimal;

public record AccountResponse(
<<<<<<< HEAD
        String actNo,
        String actName,
        String alias,
        BigDecimal balance,
        BigDecimal transferLimit,
=======
        String actName,
        String alias,
        BigDecimal balance,
>>>>>>> origin/homework4
        AccountTypeResponse accountTypeResponse,
        UserResponse user
) {
}
