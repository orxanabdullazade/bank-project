package com.unibank.unitech.service;

import com.unibank.unitech.request.TransferRequest;
import com.unibank.unitech.response.AccountResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    List<AccountResponse> getAccounts(Long userId);

    void transferAccountToAccount(TransferRequest transferRequest);
}
