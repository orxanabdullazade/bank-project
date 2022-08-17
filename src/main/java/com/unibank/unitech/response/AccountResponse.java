package com.unibank.unitech.response;

import com.unibank.unitech.model.Account;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

     long id;

     double balance;

     int status;

    public AccountResponse(Account account) {
        this.id = account.getId();
        this.balance = account.getBalance();
        this.status = account.getStatus();
    }
}
