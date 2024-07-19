package com.regression.framework.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"classpath:features/user-layer"},
        glue = {"com.regression.framework.stepdefs"},
        tags = "not @bug and not @flaking",
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)
public class ApiTestRunnerIT extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
