package com.unibank.unitech.dto;

import com.unibank.unitech.model.Account;
import lombok.Data;

import java.util.List;

@Data
public class AccountDto {

     long id;

     double balance;

     int status;

     List<AuthDto> userList;

    public AccountDto(Account account, List<AuthDto> userList) {
        this.id = account.getId();
        this.balance = account.getBalance();
        this.status = account.getStatus();
        this.userList = userList;
    }
}
