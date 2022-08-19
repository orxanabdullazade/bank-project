package com.unibank.unitech.service.impl;

import com.unibank.unitech.exception.CustomErrorException;
import com.unibank.unitech.exception.ErrorCodeEnum;
import com.unibank.unitech.model.User;
import com.unibank.unitech.repository.UserRepository;
import com.unibank.unitech.request.AuthRequest;
import com.unibank.unitech.request.CurrencyDto;
import com.unibank.unitech.response.BaseResponse;
import com.unibank.unitech.service.AuthService;
import com.unibank.unitech.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Date;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {


    @Override
    public CurrencyDto currencyRate() {

       CurrencyDto currencyDto=CurrencyDto
               .builder()
               .base("USD")
               .date(new Date().toString())
               .build();

       return currencyDto;

    }
}
