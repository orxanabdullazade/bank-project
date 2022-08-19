package com.unibank.unitech.controller;

import com.unibank.unitech.request.CurrencyDto;
import com.unibank.unitech.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {

    public final CurrencyService clientService;

    @GetMapping
    public ResponseEntity<CurrencyDto> currencyRate()
    {
        return ResponseEntity.ok(clientService.currencyRate());
    }


}
