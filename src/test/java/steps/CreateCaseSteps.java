package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Case;
import org.openqa.selenium.WebDriver;
import pages.CreateCasePage;

@Log4j2
public class CreateCaseSteps {
    private CreateCasePage createCasePage;

    public CreateCaseSteps(WebDriver driver) {
        createCasePage = new CreateCasePage(driver);
    }

    @Step("Populating Create New Case form")
    public CreateCaseSteps populateNewSuiteFormFull(Case model) {
        createCasePage.isPageOpened().populateForm(model);
        return this;
    }
}
