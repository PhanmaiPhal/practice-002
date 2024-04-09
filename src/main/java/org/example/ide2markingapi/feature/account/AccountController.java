package org.example.ide2markingapi.feature.account;

<<<<<<< HEAD

=======
>>>>>>> origin/homework4
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ide2markingapi.feature.account.dto.AccountCreateRequest;
import org.example.ide2markingapi.feature.account.dto.AccountRenameRequest;
import org.example.ide2markingapi.feature.account.dto.AccountResponse;
import org.example.ide2markingapi.feature.account.dto.AccountTransferLimitRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
<<<<<<< HEAD
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    Page<AccountResponse> findList(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int size

    ){
        return accountService.findAllAccount(page, size);
    }

    @PutMapping("/{actNo}/hide")
    void hideAccountByActNo(@PathVariable String actNo) {
        accountService.hideAccountByActNo(actNo);
    }

    @PutMapping("/{actNo}/rename")
    AccountResponse renameByActNo(@PathVariable String actNo, @RequestBody AccountRenameRequest accountRenameRequest){
        return accountService.renameByActNo(actNo, accountRenameRequest);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNew(@Valid @RequestBody AccountCreateRequest accountCreateRequest) {
        accountService.createNew(accountCreateRequest);
    }

=======
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;
    @GetMapping
    Page<AccountResponse> findList(
            @RequestParam(required = false,defaultValue = "0") int page,
            @RequestParam(required = false,defaultValue = "25") int size
    ){
        return accountService.findList(page,size);
    }
    @PutMapping("/{actNo}/rename")
    AccountResponse renameByActNo(@PathVariable String actNo,
                                  @Valid @RequestBody AccountRenameRequest accountRenameRequest){
        return accountService.renameByActNo(actNo,accountRenameRequest);
    }

    @PutMapping("{actNo}/hide")
    void hideAccountByActNo(@PathVariable String actNo){
        accountService.hideAccount(actNo);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNew(@Valid @RequestBody AccountCreateRequest accountCreateRequest){
        accountService.createNew(accountCreateRequest);
    }
>>>>>>> origin/homework4
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{actNo}")
    AccountResponse findAccountByActNo (@PathVariable String actNo){
        return  accountService.findAccountByActNumber(actNo);
    }

    @PutMapping("/{actNo}/transfer-limit")
    AccountResponse updateTransferLimit(@PathVariable String actNo, @RequestBody AccountTransferLimitRequest accountTransferLimitRequest){
        return accountService.updateTransferLimit(actNo,accountTransferLimitRequest);
    }
<<<<<<< HEAD

}

=======
}
>>>>>>> origin/homework4
