package com.unibank.unitech.service.impl;

import com.unibank.unitech.dto.AccountDto;
import com.unibank.unitech.repository.AccountRepository;
import com.unibank.unitech.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {


    public final AccountRepository accountRepository;

    @Override
    public List<AccountDto> getAccounts(Long userId) {



    }
}
