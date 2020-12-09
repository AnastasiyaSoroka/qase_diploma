package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AllureUtils;

@Log4j2
public class LoginPage extends BasePage {

    private final By EMAIL_INPUT = By.id("inputEmail");
    private final By PASSWORD_INPUT = By.id("inputPassword");
    private final By LOGIN_BUTTON = By.id("btnLogin");
    private final By ERROR = By.cssSelector("form-control-feedback");

    private String endpoint = "login";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Login page was opened")
    public LoginPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        } catch (TimeoutException ex) {
            log.fatal("Login Page is not opened. Failed with " + ex.getMessage());
        }
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    public LoginPage openPage() {
        log.info("Login page URL is " + URL + endpoint);
        driver.get(URL + endpoint);
        return this;
    }
}
