package com.regression.framework.service.util;

import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Service;

@Service
@ScenarioScope
public class ParserService {
    public double parseDollar(final String balanceText) {
        return Double.parseDouble(balanceText.replace("$", ""));
    }
}
