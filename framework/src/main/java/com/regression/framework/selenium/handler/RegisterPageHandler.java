package com.regression.framework.selenium.handler;

import com.regression.framework.config.ParabankConfig;
import com.regression.framework.selenium.WebDriverFactory;
import com.regression.framework.selenium.WebDriverWaitFactory;
import com.regression.framework.selenium.model.ContextUser;
import com.regression.framework.selenium.pom.RegisterPage;
import com.regression.framework.service.util.FakerService;
import io.cucumber.spring.ScenarioScope;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@ScenarioScope
@Service
public class RegisterPageHandler extends BasePageHandler {
    private static final Logger LOG = LogManager.getLogger(RegisterPageHandler.class);
    private final String PAGE_NAME = "register";
    private final RegisterPage registerPage;
    private final FakerService fakerService;

    protected RegisterPageHandler(final WebDriverWaitFactory webDriverWaitFactory, final WebDriverFactory driverFactory, final RegisterPage registerPage, final FakerService fakerService, final ParabankConfig parabankConfig) {
        super(webDriverWaitFactory, driverFactory, parabankConfig);
        this.registerPage = registerPage;
        this.fakerService = fakerService;
    }


    public void open() {
        goTo();
        isAt();
    }

    public ContextUser initContextUser() {
        ContextUser user = new ContextUser();

        user.setFirstName(fakerService.name().firstName());
        user.setLastName(fakerService.name().lastName());
        user.setStreet(fakerService.address().streetAddress());
        user.setCity(fakerService.address().city());
        user.setState(fakerService.address().state());
        user.setZipCode(fakerService.address().zipCode());
        user.setPhone(fakerService.phoneNumber().phoneNumber());
        user.setSsn(fakerService.generateSsn());

        user.setUsername(fakerService.name().username());
        user.setPassword(fakerService.generatePassword());

        LOG.info("User: {}", user);

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


    public boolean isLoggedIn() {
        return this.defaultWaitFor().until((driver -> registerPage.getLogoutButton().isDisplayed()));
    }

    public void logOut() {
        registerPage.getLogoutButton().click();
    }

    @Override
    protected boolean isAt() {
        return this.defaultWaitFor().until((driver -> registerPage.getFirstNameInputField().isDisplayed()));
    }

    @Override
    protected void goTo() {
        String url = StringUtils.replace(parabankConfig.getUrl(), "{pageName}", PAGE_NAME);
        driverFactory.getDriver().get(url);
    }
}
