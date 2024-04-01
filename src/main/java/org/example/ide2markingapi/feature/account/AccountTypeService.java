package org.example.ide2markingapi.feature.account;

import org.example.ide2markingapi.feature.account.dto.AccountTypeResponse;

import java.util.List;

public interface AccountTypeService {
    List<AccountTypeResponse> findAll();

    AccountTypeResponse findByAlias(String alias);
}
