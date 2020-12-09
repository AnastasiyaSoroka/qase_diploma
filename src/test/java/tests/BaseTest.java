package tests;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.LoginPage;
import utils.CapabilitiesGenerator;
import utils.TestListener;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {
    private WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod(description = "Opening Chrome Driver")
    public void setup(ITestContext context) {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        String variable = "driver";

        try {
            driver = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());
        } catch (IllegalStateException ex) {
            log.fatal(ex.getLocalizedMessage());
        }

        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);

        log.info("Setting driver into context with variable name " + variable);
        context.setAttribute(variable, driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
