package pages;

import elements.Input;
import elements.InputWithPane;
import elements.InputWithSearch;
import lombok.extern.log4j.Log4j2;
import models.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CreateCasePage extends BasePage {

    private final By SAVE_BUTTON = By.xpath("//*[text()='Save']");
    private final By CASE_TITLE_INPUT = By.id("title");
    private static final String endpointPattern = "case/%s/create";
    public static String endpoint;

    public CreateCasePage(WebDriver driver) {
        super(driver);
    }

    public CreateCasePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SAVE_BUTTON));
        return this;
    }

    public void setUrl(String code) {
        endpoint = String.format(endpointPattern, code);
    }

    public CreateCasePage openPage() {
        log.info("Create Case page URL is " + URLAPP + endpoint);
        driver.get(URLAPP + endpoint);
        return this;
    }

    public CreateCasePage setSuiteTitle(String title) {
        new Input(driver, "Case Title", CASE_TITLE_INPUT).write(title);
        return this;
    }

    public CreateCasePage clickSaveButton() {
        driver.findElement(SAVE_BUTTON).click();
        return this;
    }

    public CreateCasePage populateForm(TestCase model) {
        setSuiteTitle(model.getTitle());
        new InputWithPane(driver, "Description").write(model.getDescription());
        new InputWithSearch(driver, "status").selectWithSearch(model.getStatus());
        new InputWithSearch(driver, "severity").selectWithSearch(model.getSeverity());
        new InputWithSearch(driver, "priority").selectWithSearch(model.getPriority());
        new InputWithSearch(driver, "type").selectWithSearch(model.getType());
        new InputWithSearch(driver, "behavior").selectWithSearch(model.getBehavior());
        new InputWithSearch(driver, "automationStatus").selectWithSearch(model.getAutomation());
        clickSaveButton();
        return this;
    }
}
