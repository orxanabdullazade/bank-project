package com.unibank.unitech.service;

import com.unibank.unitech.dto.AccountDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {


    List<AccountDto> getAccounts(Long userId);
}
