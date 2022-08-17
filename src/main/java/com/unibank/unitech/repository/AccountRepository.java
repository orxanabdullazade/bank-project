package com.unibank.unitech.repository;

import com.unibank.unitech.model.Account;
import com.unibank.unitech.service.AccountService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {


}
