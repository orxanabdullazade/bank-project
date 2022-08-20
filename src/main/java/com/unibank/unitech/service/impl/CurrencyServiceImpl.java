package com.unibank.unitech.service.impl;

import com.unibank.unitech.exception.CustomErrorException;
import com.unibank.unitech.exception.ErrorCodeEnum;
import com.unibank.unitech.model.Currency;
import com.unibank.unitech.repository.CurrencyRepository;

import com.unibank.unitech.request.CurrencyDto;
import com.unibank.unitech.response.CurrencyResponse;
import com.unibank.unitech.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


        List<Currency> currencyList = currencyRepository.findAll();

        currencyList.forEach(currency -> {
            currency.setRate(currency.getRate() + value);
        });

//        return currencyRepository.save(currencyList);

        return currencyList.stream().map(currency -> new CurrencyDto(currency)).collect(Collectors.toList());

    }

    @Override
    public CurrencyResponse exchangeCurrency(String from, String to) {
        List<String> codes = new ArrayList<>();
        codes.add("AZN");
        codes.add("TL");
        codes.add("USD");
        codes.add("GBR");
        codes.add("AED");

        if (codes.contains(from) && codes.contains(to)) {

            Currency currencyFrom = currencyRepository.findByCode(from).get();
            Currency currencyTo = currencyRepository.findByCode(to).get();

            double rate = 1.0;

            if (from == "USD" && to == "USD") {
                rate = 1;
            }
            if (from == "USD" && to != "USD") {
                rate = currencyTo.getRate();
            }
            if (from != "USD" && to == "USD") {
                rate = 1 / currencyFrom.getRate();
            }
            if (from != "USD" && to != "USD") {
                rate = currencyTo.getRate() / currencyFrom.getRate();
            }

            return CurrencyResponse.builder()
                    .from(currencyFrom.getCode())
                    .to(currencyTo.getCode())
                    .rate(rate)
                    .build();

        } else {
            throw new CustomErrorException(ErrorCodeEnum.UNKNOWN_ERROR);
        }
    }
}
