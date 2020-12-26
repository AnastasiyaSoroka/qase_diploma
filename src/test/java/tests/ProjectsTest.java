package tests;

import io.qameta.allure.Feature;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RandomString;

@Feature("Projects")
public class ProjectsTest extends BaseTest {

    RandomString randomString = new RandomString();

    @Test(description = "Verify that New project was created")
    public void checkNewProjectCreated() {
        Project model = Project.builder()
                .title(randomString.StringRandom(4))
                .code(randomString.StringRandom(4))
                .description(randomString.StringRandom(4))
                .build();

        loginSteps.validLogin(USERNAME, PASSWORD);
        projectsSteps.clickCreateNewProject();
        createProjectSteps.populateNewProjectFormFull(model);
        Assert.assertEquals(projectSteps.getProjectName(model.getCode()), model.getTitle(), "Project name does not match to expected");
    }

    @Test(description = "Verify that Code value cant be shorter than 2 characters")
    public void checkErrorWithShortCode() {
        Project model = Project.builder()
                .title(randomString.StringRandom(4))
                .code("e")
                .description(randomString.StringRandom(4))
                .build();

        loginSteps.validLogin(USERNAME, PASSWORD);
        projectsSteps.clickCreateNewProject();
        String errorMessage = createProjectSteps.populateNewProjectFormFull(model).checkShortCodeMessage(model);
        Assert.assertEquals(errorMessage, "The code must be at least 2 characters.", "Error message is not correct");
    }

    @Test(description = "Verify that a project with the Code used in another project cant be created")
    public void checkErrorWithTheSameCode() {
        String code = randomString.StringRandom(4);
        Project project1 = Project.builder()
                .title(randomString.StringRandom(4))
                .code(code)
                .description(randomString.StringRandom(4))
                .build();

        Project project2 = Project.builder()
                .title(randomString.StringRandom(4))
                .code(code)
                .description(randomString.StringRandom(4))
                .build();

        loginSteps.validLogin(USERNAME, PASSWORD);
        projectsSteps.clickCreateNewProject();
        createProjectSteps.populateNewProjectFormFull(project1);
        projectsSteps.openProjectsPage().clickCreateNewProject();
        String alertMessage = createProjectSteps.populateNewProjectFormFull(project2).checkSameCodeMessage(project2);
        Assert.assertEquals(alertMessage, "Project with the same code already exists.", "Alert message is not correct");
    }
}
