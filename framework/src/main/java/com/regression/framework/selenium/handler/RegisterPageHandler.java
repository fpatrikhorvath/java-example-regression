package com.regression.framework.selenium.handler;

import com.regression.framework.selenium.model.ContextUser;
import com.regression.framework.selenium.pom.RegisterPage;
import com.regression.framework.service.util.RandomService;
import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Service;

@ScenarioScope
@Service
public class RegisterPageHandler {
    private final RegisterPage registerPage;
    private final RandomService randomService;

    public RegisterPageHandler(final RegisterPage registerPage,
                               final RandomService randomService) {
        this.registerPage = registerPage;
        this.randomService = randomService;
    }

    public void open() {
        registerPage.goTo();
        registerPage.isAt();
    }

    //TODO: writing proper generators
    public ContextUser initContextUser() {
        ContextUser user = new ContextUser();

        user.setFirstName(randomService.getRandomString(5));
        user.setLastName(randomService.getRandomString(5));
        user.setStreet(randomService.getRandomString(6));
        user.setCity(randomService.getRandomString(7));
        user.setState(randomService.getRandomString(6));
        user.setZipCode(randomService.getRandomNumber(1000, 9999));
        user.setPhone(randomService.getRandomNumber(1000, 9999));
        user.setSsn(randomService.getRandomString(6));

        user.setUsername(randomService.getRandomString(11));
        user.setPassword(randomService.getRandomString(11));

        return user;
    }

    public void register(final ContextUser user) {

        registerPage.getFirstNameInputField().sendKeys(user.getFirstName());
        registerPage.getLastNameInputField().sendKeys(user.getLastName());
        registerPage.getStreetInputField().sendKeys(user.getStreet());
        registerPage.getCityInputField().sendKeys(user.getCity());
        registerPage.getStateInputField().sendKeys(user.getFirstName());
        registerPage.getZipCodeInputField().sendKeys(String.valueOf(user.getZipCode()));
        registerPage.getPhoneInputField().sendKeys(String.valueOf(user.getPhone()));
        registerPage.getSsnInputField().sendKeys(user.getSsn());

        registerPage.getUsernameInputField().sendKeys(user.getUsername());
        registerPage.getPasswordInputField().sendKeys(user.getPassword());
        registerPage.getPasswordAgainInputField().sendKeys(user.getPassword());

        registerPage.getRegisterButton().click();
    }
}
