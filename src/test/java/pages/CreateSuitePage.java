package pages;

import elements.Input;
import elements.TextArea;
import lombok.extern.log4j.Log4j2;
import models.Suite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CreateSuitePage extends BasePage {

    private final By SAVE_BUTTON = By.id("saveButton");
    private final By SUITE_NAME_INPUT = By.id("inputTitle");
    private final String span_locator = "//label[text()='%s']//following-sibling::*//span[@role]";

    public CreateSuitePage(WebDriver driver) {
        super(driver);
    }

    public CreateSuitePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SAVE_BUTTON));
        return this;
    }

    public CreateSuitePage openPage() {
        return this;
    }

    public CreateSuitePage setSuiteTitle(String title) {
        new Input(driver, "Suite Name", SUITE_NAME_INPUT).write(title);
        return this;
    }

    public CreateSuitePage setSuiteDescription(String description) {
        new TextArea(driver, "Description").write(description);
        return this;
    }

    public CreateSuitePage setSuitePreconditions(String preconditions) {
        new TextArea(driver, "Preconditions").write(preconditions);
        return this;
    }

    public CreateSuitePage clickSubmitButton() {
        driver.findElement(SAVE_BUTTON).click();
        return this;
    }

    public CreateSuitePage populateForm(Suite model) {
        setSuiteTitle(model.getTitle());
        setSuiteDescription(model.getDescription());
        setSuitePreconditions(model.getPreconditions());
        clickSubmitButton();
        return this;
    }


}
