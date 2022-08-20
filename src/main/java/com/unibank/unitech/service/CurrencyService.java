package com.unibank.unitech.service;

import com.unibank.unitech.request.CurrencyDto;
import com.unibank.unitech.response.CurrencyResponse;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CurrencyService {

    List<CurrencyDto> currencyRate();

    CurrencyResponse exchangeCurrency(String from, String to);

}
