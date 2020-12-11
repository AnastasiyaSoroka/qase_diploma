package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class LoginPage extends BasePage {

    private final By EMAIL_INPUT = By.id("inputEmail");
    private final By PASSWORD_INPUT = By.id("inputPassword");
    private final By LOGIN_BUTTON = By.id("btnLogin");
    private final By ERROR = By.cssSelector(".form-control-feedback");

    private static final String endpoint = "login";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        } catch (TimeoutException ex) {
            log.fatal("Login Page is not opened. Failed with " + ex.getMessage());
        }
        return this;
    }

    public LoginPage openPage() {
        log.info("Login page URL is " + URLAPP + endpoint);
        driver.get(URLAPP + endpoint);
        return this;
    }

    public LoginPage attemptLogin(String username, String password) {
        driver.findElement(EMAIL_INPUT).sendKeys(username);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return this;
    }

    public void isErrorAppears() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR));
    }

    public String getErrorText() {
        isErrorAppears();
        log.info("The following error appears: " + driver.findElement(ERROR).getText());
        return driver.findElement(ERROR).getText();
    }
}
