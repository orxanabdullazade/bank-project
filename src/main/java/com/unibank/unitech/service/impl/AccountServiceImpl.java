package com.unibank.unitech.service.impl;

import com.unibank.unitech.exception.CustomErrorException;
import com.unibank.unitech.exception.ErrorCodeEnum;
import com.unibank.unitech.model.Account;
import com.unibank.unitech.request.TransferRequest;
import com.unibank.unitech.response.AccountResponse;
import com.unibank.unitech.repository.AccountRepository;
import com.unibank.unitech.response.TransferResponse;
import com.unibank.unitech.service.AccountService;
import com.unibank.unitech.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    public final AccountRepository accountRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public List<AccountResponse> getAccounts(Long userId,String bearer) {
        if ( !jwtTokenUtil.validateToken(bearer)) throw new CustomErrorException(ErrorCodeEnum.UNAUTHORIZED);

        List<Account> accountsList = accountRepository.findByUserIdAndStatus(userId,1);

        return accountsList.stream().map(account -> new AccountResponse(account)).collect(Collectors.toList());
    }

    @Override
    public TransferResponse transferAccountToAccount(TransferRequest transferRequest, String bearer) {
        if ( !jwtTokenUtil.validateToken(bearer)) throw new CustomErrorException(ErrorCodeEnum.UNAUTHORIZED);

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

        return TransferResponse
                .builder()
                .success("true")
                .build();

    }


}
