package com.regression.framework.selenium;

import com.regression.framework.config.SeleniumConfig;
import io.cucumber.spring.ScenarioScope;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.stereotype.Component;

import java.time.Duration;

@ScenarioScope
@Component
public class WebDriverWaitFactory {
    private static final Logger logger = LogManager.getLogger(WebDriverWaitFactory.class);
    private final SeleniumConfig seleniumConfig;

    public WebDriverWaitFactory(final SeleniumConfig seleniumConfig) {
        this.seleniumConfig = seleniumConfig;
    }

    public Wait<WebDriver> defaultWait(final WebDriver driver) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(seleniumConfig.getTimeout()));
    }
    public void delay(final int seconds){
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException("Delay error: " + e.getMessage());
        }
    }
}
