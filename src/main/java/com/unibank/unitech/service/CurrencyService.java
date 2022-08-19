package com.unibank.unitech.service;

import com.unibank.unitech.request.CurrencyDto;
import org.springframework.stereotype.Service;


@Service
public interface CurrencyService {

    CurrencyDto currencyRate();

}
