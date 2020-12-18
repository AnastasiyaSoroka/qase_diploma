package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
public class InputWithSearch {
    WebDriver driver;
    String label;
    String locator = "//label[@for='%s']//following-sibling::*//input";
    String locatorSearchOption = "//*[contains(@id, 'react-select') and contains(text(),'%s')]";

    public InputWithSearch(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void selectWithSearch(String text) {
        log.info(String.format("Writing text '%s' into input with label '%s'", text, label));
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(locator, label))));
        driver.findElement(By.xpath(String.format(locator, label))).sendKeys(text);
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(locatorSearchOption, text))));
        driver.findElement(By.xpath(String.format(locatorSearchOption, text))).click();

    }
}
