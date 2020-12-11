package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AllureUtils;

@Log4j2
public class ProjectsListPage extends BasePage {
    private final By CREATE_BUTTON = By.id("createButton");
    private static final String endpoint = "projects";

    public ProjectsListPage(WebDriver driver) {
        super(driver);
    }

    @Step("Projects List page was opened")
    public ProjectsListPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(CREATE_BUTTON));
        } catch (TimeoutException ex) {
            log.fatal("Login Page is not opened. Failed with " + ex.getMessage());
        }
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    public ProjectsListPage openPage() {
        log.info("Projects page URL is " + URLAPP + endpoint);
        driver.get(URLAPP + endpoint);
        return this;
    }

}
