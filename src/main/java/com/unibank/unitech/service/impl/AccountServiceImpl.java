package com.unibank.unitech.service.impl;

import com.unibank.unitech.exception.CustomErrorException;
import com.unibank.unitech.exception.ErrorCodeEnum;
import com.unibank.unitech.model.Account;
import com.unibank.unitech.request.TransferRequest;
import com.unibank.unitech.response.AccountResponse;
import com.unibank.unitech.repository.AccountRepository;
import com.unibank.unitech.response.BaseResponse;
import com.unibank.unitech.service.AccountService;
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

        List<Account> accountsList = accountRepository.findByStatus(1);

        return accountsList.stream().map(account -> new AccountResponse(account)).collect(Collectors.toList());
    }

    @Override
    public BaseResponse transferAccountToAccount(TransferRequest transferRequest) {
        Account accountFrom = accountRepository.findById(transferRequest.getAccountFrom())
                .orElseThrow(() -> new CustomErrorException(ErrorCodeEnum.NOT_FOUND_ACCOUNT));
        Account accountTo = accountRepository.findById(transferRequest.getAccountTo())
                .orElseThrow(() -> new CustomErrorException(ErrorCodeEnum.TRANSFER_NON_EXISTING_ACCOUNT));


        if (accountFrom.getBalance() < transferRequest.getAmount()) {
            throw new CustomErrorException(ErrorCodeEnum.BALANCE_LOW);
        }
        if (accountFrom.getId() == accountTo.getId()) {
            throw new CustomErrorException(ErrorCodeEnum.TRANSFER_SAME_ACCOUNT);
        }
        if (accountTo.getStatus() == 0) {
            throw new CustomErrorException(ErrorCodeEnum.TRANSFER_DEACTIVE_ACCOUNT);
        }


        accountFrom.setBalance(accountFrom.getBalance() - transferRequest.getAmount());
        accountRepository.save(accountFrom);
        accountTo.setBalance(accountTo.getBalance() + transferRequest.getAmount());
        accountRepository.save(accountTo);

        return BaseResponse
                .builder()
                .success("true")
                .build();

    }


}
