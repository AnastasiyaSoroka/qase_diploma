package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class ProjectPage extends BasePage {

    private static final By PROJECT_LABEL = By.className("header");
    private static final By CODE_LABEL = By.className("subheader");
    private static final By CREATE_SUITE_BUTTON = By.xpath("//*[contains(text(), 'Create new suite')]");
    private static final By CREATE_CASE_BUTTON = By.xpath("//*[contains(text(), 'Create new case')]");
    private static final String caseLocator = "//*[@class='case-row']//*[contains(text(),'%s')]";
    private static final String suiteLocator = "//*[@class='suite-header'][contains(text(),'%s')]";

    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    public ProjectPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CREATE_SUITE_BUTTON));
        return this;
    }

    public ProjectPage openPage() {
        return this;
    }

    public String getProjectNameText() {
        return driver.findElement(PROJECT_LABEL).getText();
    }

    public String getProjectCodeText() {
        return driver.findElement(CODE_LABEL).getText();
    }

    public ProjectPage clickCreateNewSuite() {
        isElementDisplayed(CREATE_SUITE_BUTTON);
        driver.findElement(CREATE_SUITE_BUTTON).click();
        return this;
    }

    public ProjectPage clickCreateNewCase() {
        isElementDisplayed(CREATE_CASE_BUTTON);
        driver.findElement(CREATE_CASE_BUTTON).click();
        return this;
    }

    public String getCaseNameText(String name) {
        isElementDisplayed(By.xpath(String.format(caseLocator, name)));
        return driver.findElement(By.xpath(String.format(caseLocator, name))).getText();
    }

    public String getSuiteNameText(String name) {
        isElementDisplayed(By.xpath(String.format(suiteLocator, name)));
        return driver.findElement(By.xpath(String.format(suiteLocator, name))).getText();
    }
}
