package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
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
        return projectPage.getProjectNameText();
    }

    @Step("Verify that project's page contains code")
    public String getProjectCode() {
        return projectPage.getProjectCodeText();
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

}
