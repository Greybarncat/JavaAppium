package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject
{
    private static final String
            LOGIN_BUTTON = "xpath://body/div/div/a[text()='Log in']",
            LOGIN_INPUT = "css:input[name='wpName']",
            PASSWORD_INPUT = "css:input[name='wpPassword']",
            SUBMIT_BUTTON = "css:button[name='wploginattempt']";

    public AuthorizationPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Clicking Log in button")
    public void clickAuthButton()
    {
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find auth button", 5);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button", 5);
    }

    @Step("Put login and password in inputs")
    public void enterLoginData(String login, String password)
    {
        this.waitForElementAndSendKeys(LOGIN_INPUT,login, "Cannot find and put a login in the login input", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT,password, "Cannot find and put a password in the password input", 5);
    }

    @Step("Clicking submit button")
    public void submitForm()
    {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click submit auth button", 5);
    }
}
