package org.example.ide2markingapi.feature.account;


import org.example.ide2markingapi.feature.account.dto.AccountCreateRequest;
import org.example.ide2markingapi.feature.account.dto.AccountRenameRequest;
import org.example.ide2markingapi.feature.account.dto.AccountResponse;
import org.example.ide2markingapi.feature.account.dto.AccountTransferLimitRequest;
import org.springframework.data.domain.Page;

public interface AccountService {
    void createNew(AccountCreateRequest accountCreateRequest);

    AccountResponse findAccountByActNumber(String accountNumber);

    AccountResponse renameByActNo(String actNo, AccountRenameRequest request);
    Page<AccountResponse> findList(int page, int size);
    void hideAccount(String actNo);

    AccountResponse updateTransferLimit(String actNo, AccountTransferLimitRequest accountTransferLimitRequest);
}
