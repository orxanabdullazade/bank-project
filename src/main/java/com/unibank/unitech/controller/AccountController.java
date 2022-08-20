package com.unibank.unitech.controller;


import com.unibank.unitech.request.TransferRequest;
import com.unibank.unitech.response.AccountResponse;
import com.unibank.unitech.response.BaseResponse;
import com.unibank.unitech.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    public final AccountService accountService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<AccountResponse>> getAccounts(@PathVariable Long userId) {
        return ResponseEntity.ok(accountService.getAccounts(userId));
    }

    @PatchMapping
    public ResponseEntity<BaseResponse> transferAccountToAccount(@RequestBody TransferRequest transferRequest)
    {
        return ResponseEntity.ok(accountService.transferAccountToAccount(transferRequest));
    }


}
