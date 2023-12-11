package ru.parcercb.service.currency;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.parcercb.model.CurrencyRateModel;
import ru.parcercb.service.parser.ParserService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {
    private final ParserService parserService;

    @Cacheable("currencyRates")
    @Override
    public List<CurrencyRateModel> getCurrencyRates() {
        return parserService.parserCB();
    }

    @CacheEvict("currencyRates")
    @Override
    public void refreshCurrencyRates() {
        log.info("очищаем кэш");
    }
}