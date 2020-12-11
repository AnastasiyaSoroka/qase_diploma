package tests;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.LoginPage;
import pages.ProjectsListPage;
import steps.LoginSteps;
import utils.CapabilitiesGenerator;
import utils.PropertyReader;
import utils.TestListener;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {
    private WebDriver driver;
    ProjectsListPage projectsListPage;
    protected LoginSteps loginSteps;

    public static final String USERNAME = System.getenv().getOrDefault("username", PropertyReader.getProperty("username"));
    public static final String PASSWORD = System.getenv().getOrDefault("password", PropertyReader.getProperty("password"));

    @BeforeMethod(description = "Opening Chrome Driver")
    public void setup(ITestContext context) {

        String variable = "driver";

        try {
            driver = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());
        } catch (IllegalStateException ex) {
            log.fatal(ex.getLocalizedMessage());
        }

        driver.manage().window().maximize();
        loginSteps = new LoginSteps(driver);
        projectsListPage = new ProjectsListPage(driver);

        context.setAttribute(variable, driver);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (WebDriverException ex) {
                log.fatal(ex.getLocalizedMessage());
            }
        }

    }
}
