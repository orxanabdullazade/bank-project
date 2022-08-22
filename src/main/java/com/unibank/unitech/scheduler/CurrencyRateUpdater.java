package com.unibank.unitech.scheduler;

import com.unibank.unitech.model.Currency;
import com.unibank.unitech.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class CurrencyRateUpdater {

    private final CurrencyRepository currencyRepository;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void updateRates() {

        LocalDateTime beforeOneMinutes = LocalDateTime.now().minusMinutes(1);
        List<Currency> currencyList = currencyRepository.findCurrencyByCreatedDateLessThan(beforeOneMinutes);

        double leftLimit = 0.01;
        double rightLimit = -0.01;
        double generatedDouble = leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);

        currencyList.forEach(currency -> {
            currency.setRate(currency.getRate() + generatedDouble);
            currency.setCreatedDate(LocalDateTime.now());
        });
        currencyRepository.saveAll(currencyList);
    }
}
