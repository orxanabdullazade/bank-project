package com.unibank.unitech.request;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDto {

    private String base;
    private String date;
    List<String> rates;


}
