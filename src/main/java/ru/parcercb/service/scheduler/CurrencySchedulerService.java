package ru.parcercb.service.scheduler;

import org.springframework.stereotype.Service;

@Service
public interface CurrencySchedulerService {
    void updateCurrencyRates();
}