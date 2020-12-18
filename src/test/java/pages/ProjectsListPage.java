package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class ProjectsListPage extends BasePage {
    private final By CREATE_BUTTON = By.id("createButton");
    private static final String endpoint = "projects";
    private static final String projectLocator = "//*[@class='project-row']//*[contains(text(),'%s')]";

    public ProjectsListPage(WebDriver driver) {
        super(driver);
    }

    @Step("Projects List page was opened")
    public ProjectsListPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CREATE_BUTTON));
        return this;
    }

    public ProjectsListPage openPage() {
        log.info("Projects page URL is " + URLAPP + endpoint);
        driver.get(URLAPP + endpoint);
        return this;
    }

    public ProjectsListPage clickCreateNewProject() {
        driver.findElement(CREATE_BUTTON).click();
        return this;
    }

    public String getProjectNameText(String name) {
        isElementDisplayed(By.xpath(String.format(projectLocator, name)));
        return driver.findElement(By.xpath(String.format(projectLocator, name))).getText();
    }

}