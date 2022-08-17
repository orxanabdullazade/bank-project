package com.unibank.unitech.service.impl;

import com.unibank.unitech.model.Account;
import com.unibank.unitech.response.AccountResponse;
import com.unibank.unitech.repository.AccountRepository;
import com.unibank.unitech.response.UserResponse;
import com.unibank.unitech.service.AccountService;
import com.unibank.unitech.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {


    public final AccountRepository accountRepository;

    @Override
    public List<AccountResponse> getAccounts(Long userId) {

        List<Account> accountsList = accountRepository.findAll();

        return accountsList.stream()
                .map(account -> {
                    return new AccountResponse(account);
                 }).collect(Collectors.toList());
    }



}
