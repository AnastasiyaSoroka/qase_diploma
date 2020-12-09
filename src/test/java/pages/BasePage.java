package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract class BasePage {
    WebDriver driver;
    WebDriverWait wait;

    public static final String URL = "https://app.qase.io/";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public abstract BasePage isPageOpened();

    public abstract BasePage openPage();

    public boolean isElementDisplayed(By locator) {
        try {
            driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;

    }
}
