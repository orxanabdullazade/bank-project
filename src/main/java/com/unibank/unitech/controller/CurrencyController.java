package com.unibank.unitech.controller;

import com.unibank.unitech.request.CurrencyDto;
import com.unibank.unitech.response.AccountResponse;
import com.unibank.unitech.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService){
        this.currencyService = currencyService;
    }

    @GetMapping
    public ResponseEntity<List<CurrencyDto>> currencyRate()
    {
        return ResponseEntity.ok(currencyService.currencyRate());
    }

    @GetMapping("/{from}/{to}")
    public ResponseEntity<CurrencyDto> exchangeCurrency(@PathVariable String from,@PathVariable String to) {
        return ResponseEntity.ok(currencyService.exchangeCurrency(from,to));
    }


}
