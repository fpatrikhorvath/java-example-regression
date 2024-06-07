package com.regression.framework.service.util;

import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@ScenarioScope
public class RandomService {
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final Random random = new SecureRandom();

    public String getRandomString(final int length) {
        return IntStream.range(0, length).
                mapToObj(i -> String.valueOf(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length()))))
                .collect(Collectors.joining());
    }
}
