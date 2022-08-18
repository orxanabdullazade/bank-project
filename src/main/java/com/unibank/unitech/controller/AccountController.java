package com.unibank.unitech.controller;


import com.unibank.unitech.request.TransferRequest;
import com.unibank.unitech.response.AccountResponse;
import com.unibank.unitech.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    public final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<AccountResponse>> getAccounts(@PathVariable Long userId) {
        return ResponseEntity.ok(accountService.getAccounts(userId));
    }


    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void transferAccountToAccount(@RequestBody TransferRequest transferRequest)
    {
        accountService.transferAccountToAccount(transferRequest);
    }










}
