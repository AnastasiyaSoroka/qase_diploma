package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.NewProjectUI;
import org.openqa.selenium.WebDriver;
import pages.ProjectsListPage;

@Log4j2
public class ProjectsSteps {
    private ProjectsListPage projectsListPage;

    public ProjectsSteps(WebDriver driver) {
        projectsListPage = new ProjectsListPage(driver);
    }

    @Step("Click Create New Project")
    public ProjectsSteps clickCreateNewProject() {
        projectsListPage.isPageOpened().clickCreateNewProject();
        return this;
    }

    @Step("Open Projects page")
    public ProjectsSteps openProjectsPage() {
        projectsListPage.openPage().isPageOpened();
        return this;
    }

    @Step("Verify that new project exists on Projects page")
    public String getProjectName(NewProjectUI model) {
        return projectsListPage.getProjectNameText(model.getTitle());
    }

}
