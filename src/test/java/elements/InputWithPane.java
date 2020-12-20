package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
public class InputWithPane {
    WebDriver driver;
    String label;
    String textlocator = "//label[contains(text(),'%s')]/following-sibling::*//p";

    public InputWithPane(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void write(String text) {
        log.info(String.format("Writing text '%s' into input with label '%s'", text, label));
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(textlocator, label))));
        WebElement element = driver.findElement(By.xpath(String.format(textlocator, label)));
        element.clear();
        element.sendKeys(text);
    }

}
