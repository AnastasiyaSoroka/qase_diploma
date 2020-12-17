package pages;

import elements.Input;
import elements.Textarea;
import lombok.extern.log4j.Log4j2;
import models.NewProjectUI;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CreateProjectPage extends BasePage {

    private final By TITLE_INPUT = By.id("inputTitle");
    private final By CODE_INPUT = By.id("inputCode");
    private final By DESCRIPTION_INPUT = By.id("inputDescription");
    private final By SUBMIT_BUTTON = By.cssSelector("[type=submit]");
    private final By ERROR = By.cssSelector(".form-control-feedback");
    private final By ALERT = By.cssSelector(".alert-message");

    private static final String endpoint = "project/create";

    public CreateProjectPage(WebDriver driver) {
        super(driver);
    }

    public CreateProjectPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_BUTTON));
        } catch (TimeoutException ex) {
            log.fatal("New Project Page is not opened. Failed with " + ex.getMessage());
        }
        return this;
    }

    public CreateProjectPage openPage() {
        log.info("New Project page URL is " + URLAPP + endpoint);
        driver.get(URLAPP + endpoint);
        return this;
    }

    public CreateProjectPage populateForm(NewProjectUI model) {
        setProjectTitle(model.getTitle());
        setProjectCode(model.getCode());
        setProjectDescription(model.getDescription());
        clickSubmitButton();
        return this;
    }

    public CreateProjectPage clickSubmitButton() {
        driver.findElement(SUBMIT_BUTTON).click();
        return this;
    }

    public CreateProjectPage setProjectTitle(String title) {
        new Input(driver, "Title", TITLE_INPUT).write(title);
        return this;
    }

    public CreateProjectPage setProjectCode(String code) {
        new Input(driver, "Code", CODE_INPUT).write(code);
        return this;
    }

    public CreateProjectPage setProjectDescription(String description) {
        new Textarea(driver, "Description", DESCRIPTION_INPUT).write(description);
        return this;
    }

    public void isErrorAppears(By message) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(message));
    }

    public String getErrorText() {
        isErrorAppears(ERROR);
        log.info("The following error appears: " + driver.findElement(ERROR).getText());
        return driver.findElement(ERROR).getText();
    }

    public String getAlertText() {
        isErrorAppears(ALERT);
        log.info("The following alert appears: " + driver.findElement(ALERT).getText());
        return driver.findElement(ALERT).getText();
    }

}
