package ru.parcercb.service.currency;

import org.springframework.stereotype.Service;
import ru.parcercb.model.CurrencyRateModel;

import java.util.List;

@Service
public interface CurrencyService {
    List<CurrencyRateModel> getCurrencyRates();

    void refreshCurrencyRates();

}