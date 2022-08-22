package com.unibank.unitech.service;

import com.unibank.unitech.request.CurrencyRequest;
import com.unibank.unitech.response.CurrencyResponse;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CurrencyService {

    List<CurrencyRequest> currencyRate(String bearer);

    CurrencyResponse exchangeCurrency(String from, String to,String bearer);

}
