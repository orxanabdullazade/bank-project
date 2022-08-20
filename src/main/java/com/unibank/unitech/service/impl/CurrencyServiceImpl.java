package com.unibank.unitech.service.impl;

import com.unibank.unitech.model.Account;
import com.unibank.unitech.model.Currency;
import com.unibank.unitech.repository.CurrencyRepository;

import com.unibank.unitech.request.CurrencyDto;
import com.unibank.unitech.response.AccountResponse;
import com.unibank.unitech.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Override
    @Scheduled(cron = "0 0/1 * * * ?")
    public List<CurrencyDto> currencyRate() {
//        Date date = new Date(); // This object contains the current date value
//
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String simpleDateFormat=formatter.format(date);
//        List<Currency> currencyList=currencyRepository.findAllByCreatedDateLessThan(simpleDateFormat);

        int min = 1;
        int max = 10;
        Random random = new Random();
        int value = random.nextInt(max + min) + min;


        List<Currency> currencyList=currencyRepository.findAll();

        currencyList.forEach(currency -> {
             currency.setRate(currency.getRate()+value);
        });

        return currencyRepository.save(currencyList);

        //return currencyList.stream().map(currency -> new CurrencyDto(currency)).collect(Collectors.toList());

    }

    @Override
    public CurrencyDto exchangeCurrency(String from, String to) {
        return null;
    }
}
