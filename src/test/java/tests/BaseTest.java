package tests;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import steps.*;
import utils.CapabilitiesGenerator;
import utils.PropertyReader;
import utils.TestListener;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {
    private WebDriver driver;
    protected ProjectsSteps projectsSteps;
    protected LoginSteps loginSteps;
    protected CreateProjectSteps createProjectSteps;
    protected CreateSuiteSteps createSuiteSteps;
    protected CreateCaseSteps createCaseSteps;
    protected ProjectSteps projectSteps;

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
        projectsSteps = new ProjectsSteps(driver);
        createProjectSteps = new CreateProjectSteps(driver);
        createSuiteSteps = new CreateSuiteSteps(driver);
        createCaseSteps = new CreateCaseSteps(driver);
        projectSteps = new ProjectSteps(driver);

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