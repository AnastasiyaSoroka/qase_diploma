package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.NewProjectUI;
import org.openqa.selenium.WebDriver;
import pages.CreateProjectPage;

@Log4j2
public class CreateProjectSteps {
    private CreateProjectPage createProjectPage;

    public CreateProjectSteps(WebDriver driver) {
        createProjectPage = new CreateProjectPage(driver);
    }

    @Step("Populating Create New Project form with name, code and description")
    public CreateProjectSteps populateNewProjectFormFull(NewProjectUI model) {
        createProjectPage.isPageOpened().populateForm(model);
        return this;
    }

    @Step("Checking error message with short code value")
    public String checkShortCodeMessage(NewProjectUI model) {
        log.info(String.format("Short code is '%s'", model.getCode()));
        return createProjectPage.getErrorText();
    }

    @Step("Checking error message with short code value")
    public String checkSameCodeMessage(NewProjectUI model) {
        log.info(String.format("Code is '%s'", model.getCode()));
        return createProjectPage.getAlertText();
    }

}
