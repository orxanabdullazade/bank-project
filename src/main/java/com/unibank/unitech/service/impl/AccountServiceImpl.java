package com.unibank.unitech.service.impl;

import com.unibank.unitech.model.Account;
import com.unibank.unitech.model.User;
import com.unibank.unitech.request.TransferRequest;
import com.unibank.unitech.response.AccountResponse;
import com.unibank.unitech.repository.AccountRepository;
import com.unibank.unitech.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {


    public final AccountRepository accountRepository;

    @Override
    public List<AccountResponse> getAccounts(Long userId) {

        List<Account> accountsList = accountRepository.findAll();

        return accountsList.stream().map(account -> new AccountResponse(account)).collect(Collectors.toList());
    }

    @Override
    public void transferAccountToAccount(TransferRequest transferRequest) {
        Account accountFrom = accountRepository.findById(transferRequest.getAccountFrom())
                .orElseThrow(() -> new RuntimeException("dsds"));
        Account accountTo = accountRepository.findById(transferRequest.getAccountTo())
                .orElseThrow(() -> new RuntimeException("dsds"));

        if (accountFrom.getBalance() < transferRequest.getAmount()) {
            throw new RuntimeException("no enough money in my account");
        }
        if (accountFrom.getId() == accountTo.getId()) {
            throw new RuntimeException("I try to make transfer to same account");
        }
        if (accountTo.getStatus() == 0) {
            throw new RuntimeException("I try to make transfer to deactive account");
        }
        if (accountRepository.existsById(accountTo.getId())) {
            throw new RuntimeException("I try to make transfer to non existing account");
        }

        accountFrom.setBalance(accountFrom.getBalance() - transferRequest.getAmount());
        accountRepository.save(accountFrom);
        accountTo.setBalance(accountTo.getBalance() + transferRequest.getAmount());
        accountRepository.save(accountTo);

    }


}
