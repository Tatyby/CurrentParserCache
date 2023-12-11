package ru.parcercb.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.parcercb.model.CurrencyRateModel;
import ru.parcercb.service.cache.CacheService;
import ru.parcercb.service.currency.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("api/currency")
@RequiredArgsConstructor
@Slf4j
public class CurrencyController {
    private final CurrencyService currencyService;
    private final CacheService cacheService;

    @GetMapping("rates")
    public List<CurrencyRateModel> getCurrencyRates() {
        log.info("Получаем rates");
        cacheService.viewCacheContents();
        return currencyService.getCurrencyRates();
    }
}