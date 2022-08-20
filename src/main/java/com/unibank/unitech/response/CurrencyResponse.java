package com.unibank.unitech.response;


import com.unibank.unitech.model.Currency;
import com.unibank.unitech.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyResponse {

    String from;
    String to;
    double rate;

    public CurrencyResponse(Currency currency) {
        this.rate=currency.getRate();
    }
}
