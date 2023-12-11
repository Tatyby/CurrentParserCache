package ru.parcercb.service.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.parcercb.service.currency.CurrencyService;

@Component
@RequiredArgsConstructor
@Slf4j
public class CurrencySchedulerServiceImpl implements CurrencySchedulerService {
    private final CurrencyService currencyService;

    @Scheduled(fixedRate = 60 * 1000) //поставила 1 минуту, чтобы проверить
    @Override
    public void updateCurrencyRates() {
        log.info("парсим сайт ЦБ, для этого: ");
        currencyService.refreshCurrencyRates();
        log.info("очистили кэш");
        currencyService.getCurrencyRates();
        log.info("получили новые данные в кэш");
    }
}