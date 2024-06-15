package com.regression.framework.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"classpath:features/parabank"},
        glue = {"com.regression.framework.stepdefs"},
        tags = "not @bug",
        plugin = "html:target/report.html"
)
public class UserInterfaceTestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
