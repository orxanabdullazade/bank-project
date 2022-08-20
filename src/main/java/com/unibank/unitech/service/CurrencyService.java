package com.unibank.unitech.service;

import com.unibank.unitech.request.CurrencyDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface CurrencyService {

    List<CurrencyDto> currencyRate();

    CurrencyDto exchangeCurrency(String from, String to);

}
