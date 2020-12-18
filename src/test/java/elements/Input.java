package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
public class Input {
    WebDriver driver;
    String label;
    By locator;
    String textlocator = "//label[contains(text(),'%s')]/following-sibling::*//p";

    public Input(WebDriver driver, String label, By locator) {
        this.driver = driver;
        this.label = label;
        this.locator = locator;
    }

    public void write(String text) {
        log.info(String.format("Writing text '%s' into input with label '%s'", text, label));
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void writeByLocator(String text) {
        log.info(String.format("Writing text '%s' into input with label '%s'", text, label));
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(textlocator, label))));
        driver.findElement(By.xpath(String.format(textlocator, label))).clear();
        driver.findElement(By.xpath(String.format(textlocator, label))).sendKeys(text);
    }

}
