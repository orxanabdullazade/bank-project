package com.unibank.unitech.controller;

import com.unibank.unitech.request.CurrencyRequest;
import com.unibank.unitech.response.CurrencyResponse;
import com.unibank.unitech.service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")
@Tag(name = "Currency Service")
public class CurrencyController {

    private CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService){
        this.currencyService = currencyService;
    }

    @GetMapping
    @Operation(summary = "This return currency list")
    public ResponseEntity<List<CurrencyRequest>> currencyRate(@RequestHeader("Authorization") String bearer)
    {
        return ResponseEntity.ok(currencyService.currencyRate(bearer));
    }

    @GetMapping("/{from}/{to}")
    public ResponseEntity<CurrencyResponse> exchangeCurrency(@PathVariable String from, @PathVariable String to,
                                                             @RequestHeader("Authorization") String bearer) {
        return ResponseEntity.ok(currencyService.exchangeCurrency(from,to,bearer));
    }


}
