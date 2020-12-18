package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Case;
import models.Suite;
import org.openqa.selenium.WebDriver;
import pages.ProjectPage;

@Log4j2
public class ProjectSteps {
    private ProjectPage projectPage;

    public ProjectSteps(WebDriver driver) {
        projectPage = new ProjectPage(driver);
    }

    @Step("Verify that project's page was opened")
    public String getProjectName() {
        return projectPage.isPageOpened().getProjectNameText();
    }

    @Step("Verify that project's page contains code")
    public String getProjectCode() {
        return projectPage.isPageOpened().getProjectCodeText();
    }

    @Step("Click Create New Suite")
    public ProjectSteps clickCreateNewSuite() {
        projectPage.isPageOpened().clickCreateNewSuite();
        return this;
    }

    @Step("Click Create New Case")
    public ProjectSteps clickCreateNewCase() {
        projectPage.isPageOpened().clickCreateNewCase();
        return this;
    }

    @Step("Verify that new case exists on Project page")
    public String getCaseName(Case model) {
        return projectPage.getCaseNameText(model.getTitle());
    }

    @Step("Verify that new suite exists on Project page")
    public String getSuiteName(Suite model) {
        return projectPage.getSuiteNameText(model.getTitle());
    }

}
