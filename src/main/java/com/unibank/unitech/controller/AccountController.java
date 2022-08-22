package com.unibank.unitech.controller;


import com.unibank.unitech.request.TransferRequest;
import com.unibank.unitech.response.AccountResponse;
import com.unibank.unitech.response.TransferResponse;
import com.unibank.unitech.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Tag(name = "Account Service")
public class AccountController {

    public final AccountService accountService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<AccountResponse>> getAccounts(@PathVariable Long userId,@RequestHeader("Authorization") String bearer) {
        return ResponseEntity.ok(accountService.getAccounts(userId,bearer));
    }

    @PutMapping
    public ResponseEntity<TransferResponse> transferAccountToAccount(@RequestBody TransferRequest transferRequest,
                                                                     @RequestHeader("Authorization") String bearer) {
        return ResponseEntity.ok(accountService.transferAccountToAccount(transferRequest,bearer));
    }


}
