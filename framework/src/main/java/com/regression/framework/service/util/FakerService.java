package com.regression.framework.service.util;

import com.github.javafaker.Faker;
import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Service;

@Service
@ScenarioScope
public class FakerService extends Faker {

    public String generateSsn() {
        final String areaNumber   = String.format("%03d", number().numberBetween(1, 900));
        final String groupNumber  = String.format("%02d", number().numberBetween(1, 100));
        final String serialNumber = String.format("%04d", number().numberBetween(1, 10000));

        return String.join("-", areaNumber, groupNumber, serialNumber);
    }

    public String generatePassword() {
        final String upperCaseLetters = lorem().characters(4, 6, true);
        final String lowerCaseLetters = lorem().characters(4, 8, false);
        final String digits           = number().digit();
        final String specialChars     = regexify("[!@#$%^&*()_+]");

        return String.join("-", upperCaseLetters, lowerCaseLetters, digits, specialChars);
    }
}
