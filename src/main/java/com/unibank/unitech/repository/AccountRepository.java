package com.unibank.unitech.repository;

import com.unibank.unitech.model.Account;
import com.unibank.unitech.service.AccountService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {

    List<Account> findByUserIdAndStatus(long user_id,int status);

}
