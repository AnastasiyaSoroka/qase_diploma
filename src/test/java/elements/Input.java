package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
public class Input {
    WebDriver driver;
    String label;
    By locator;

    public Input(WebDriver driver, String label, By locator) {
        this.driver = driver;
        this.label = label;
        this.locator = locator;
    }

    public void write(String text) {
        log.info(String.format("Writing text '%s' into input with label '%s'", text, label));
        WebElement element = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }
}
