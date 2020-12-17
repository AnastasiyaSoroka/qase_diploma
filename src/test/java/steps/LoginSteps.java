package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

@Log4j2
public class LoginSteps {
    private LoginPage loginPage;

    public LoginSteps(WebDriver driver) {
        loginPage = new LoginPage(driver);
    }

    @Step("Checking error message with invalid credentials '{username}'/ '{password}'")
    public String checkInvalidCredentialsMessage(String username, String password) {
        log.info(String.format("Invalid login is '%s'", username));
        log.info(String.format("Invalid password is '%s'", password));

        return doingLogin(username, password).getErrorText();
    }

    @Step("Entering valid credentials")
    public LoginSteps validLogin(String username, String password) {
        doingLogin(username, password);
        return this;
    }

    private LoginPage doingLogin(String username, String password) {
        return loginPage
                .openPage()
                .isPageOpened()
                .attemptLogin(username, password);
    }

}