package com.unibank.unitech.request;

import com.unibank.unitech.model.Currency;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDto {

     String code;
     double rate;

     public CurrencyDto(Currency currency) {
          this.code=currency.getCode();
          this.rate=currency.getRate();
     }
}


