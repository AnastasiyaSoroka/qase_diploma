package pages;

import elements.Input;
import elements.InputWithSearch;
import models.Case;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateCasePage extends BasePage {

    private final By SAVE_BUTTON = By.xpath("//*[text()='Save']");
    private final By CASE_TITLE_INPUT = By.id("title");

    public CreateCasePage(WebDriver driver) {
        super(driver);
    }

    public CreateCasePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SAVE_BUTTON));
        return this;
    }

    public CreateCasePage openPage() {
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

    public CreateCasePage populateForm(Case model) {
        setSuiteTitle(model.getTitle());
        new Input(driver, "Description", null).writeByLocator(model.getDescription());
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
