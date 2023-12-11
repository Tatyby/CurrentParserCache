package ru.parcercb.service.parser;

import org.springframework.stereotype.Service;
import ru.parcercb.model.CurrencyRateModel;

import java.util.List;

@Service
public interface ParserService {
    List<CurrencyRateModel> parserCB();
}