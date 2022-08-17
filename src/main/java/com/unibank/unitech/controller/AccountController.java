package com.unibank.unitech.controller;


import com.unibank.unitech.dto.AccountDto;
import com.unibank.unitech.service.AccountService;
import com.unibank.unitech.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    public final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }



    @GetMapping("/{userId}")
    public ResponseEntity<List<AccountDto>> getAccounts(@PathVariable Long userId) {
        return ResponseEntity.ok(accountService.getAccounts(userId));
    }








}
