package com.regression.framework.selenium;

import com.regression.framework.config.SeleniumConfig;
import io.cucumber.spring.ScenarioScope;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
public class WebDriverFactory {
    private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);
    private static WebDriver driver;
    private final SeleniumConfig seleniumConfig;

    @Autowired
    public WebDriverFactory(final SeleniumConfig seleniumConfig) {
        this.seleniumConfig = seleniumConfig;
        if (driver == null) {
            logger.info("Initializing the {} driver", seleniumConfig.getBrowserType());
            switch (seleniumConfig.getBrowserType()) {
                case "chrome" -> driver = initChromeDriver();
                case "firefox" -> driver = initFirefoxDriver();
                default -> throw new RuntimeException("The given browser is not an option!");
            }
            WebDriverInitializationListener.setIsInitialized(true);
            logger.info("Chrome driver initialized: {}", WebDriverInitializationListener.isInitialized());
        }
    }

    private ChromeDriver initChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        if (seleniumConfig.isHeadless()) {
            options.addArguments("--headless");
        }
        return new ChromeDriver(options);
    }

    private FirefoxDriver initFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        if (seleniumConfig.isHeadless()) {
            options.addArguments("--headless");
        }
        return new FirefoxDriver(options);
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}