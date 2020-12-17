package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
public class Textarea {
    WebDriver driver;
    String label;
    By locator;

    public Textarea(WebDriver driver, String label, By locator) {
        this.driver = driver;
        this.label = label;
        this.locator = locator;
    }

    public void write(String text) {
        log.info(String.format("Writing text '%s' into textarea with label '%s'", text, label));
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }
}
