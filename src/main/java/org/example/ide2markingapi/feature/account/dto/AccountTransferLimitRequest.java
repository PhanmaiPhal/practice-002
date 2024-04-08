package org.example.ide2markingapi.feature.account.dto;

import java.math.BigDecimal;

public record AccountTransferLimitRequest(
        BigDecimal transferLimit
) {
}
