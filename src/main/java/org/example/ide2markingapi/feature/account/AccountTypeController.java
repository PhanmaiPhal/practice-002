package org.example.ide2markingapi.feature.account;

import lombok.RequiredArgsConstructor;
import org.example.ide2markingapi.feature.account.dto.AccountTypeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/account-types")
public class AccountTypeController {

    private final AccountTypeService accountTypeService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AccountTypeResponse> findAccountTypes(){
        return accountTypeService.findAll();
    }
    @GetMapping("/{alias}")
    public AccountTypeResponse findByAlias(@PathVariable String alias){
        return accountTypeService.findByAlias(alias);
    }

}