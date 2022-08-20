package com.unibank.unitech.repository;

import com.unibank.unitech.model.Currency;
import com.unibank.unitech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Long> {

//      List<Currency> findAllByCreatedDateLessThan(String date);
       Optional<Currency> findByCode(String code);

}
