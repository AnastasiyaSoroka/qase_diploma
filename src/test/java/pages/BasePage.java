package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

abstract class BasePage {
    WebDriver driver;
    WebDriverWait wait;

    public static final String URLAPP = System.getenv().getOrDefault("urlApp", PropertyReader.getProperty("urlApp"));

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public abstract BasePage isPageOpened();

    public abstract BasePage openPage();

    public boolean isElementDisplayed(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;

    }
}
