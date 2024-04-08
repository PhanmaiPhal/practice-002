package org.example.ide2markingapi.feature.accountType;

import org.example.ide2markingapi.feature.accountType.dto.AccountTypeResponse;

import java.util.List;

public interface AccountTypeService {
    List<AccountTypeResponse> findAll();

    AccountTypeResponse findByAlias(String alias);


}
