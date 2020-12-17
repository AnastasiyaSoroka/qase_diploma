package tests;

import models.NewProjectUI;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class ProjectsTest extends BaseTest {

    Random random = new Random();
    String name = "n";

    @Test(description = "Verify that New project was created and was added to the Projects page")
    public void checkNewProjectIsOnProjectsPage() {

        NewProjectUI model = NewProjectUI.builder()
                .title(name + random.nextInt(1000))
                .code(name + random.nextInt(1000))
                .description(name + random.nextInt(1000))
                .build();

        loginSteps.validLogin(USERNAME, PASSWORD);
        projectsSteps.clickCreateNewProject();
        createProjectSteps.populateNewProjectFormFull(model);
        Assert.assertEquals(projectsSteps.openProjectsPage().getProjectName(model), model.getTitle(), "Project name does not match to expected");
    }


    @Test(description = "Verify that New project was created")
    public void checkNewProjectCreated() {
        NewProjectUI model = NewProjectUI.builder()
                .title(name + random.nextInt(1000))
                .code(name + random.nextInt(1000))
                .description(name + random.nextInt(1000))
                .build();

        loginSteps.validLogin(USERNAME, PASSWORD);
        projectsSteps.clickCreateNewProject();
        createProjectSteps.populateNewProjectFormFull(model);
        Assert.assertEquals(projectSteps.getProjectName(), model.getTitle(), "Project name does not match to expected");
    }

    @Test(description = "Verify that Code value cant be shorter than 2 characters")
    public void checkErrorWithShortCode() {
        NewProjectUI model = NewProjectUI.builder()
                .title(name + random.nextInt(1000))
                .code("e")
                .description(name + random.nextInt(1000))
                .build();

        loginSteps.validLogin(USERNAME, PASSWORD);
        projectsSteps.clickCreateNewProject();
        String errorMessage = createProjectSteps.populateNewProjectFormFull(model).checkShortCodeMessage(model);
        Assert.assertEquals(errorMessage, "The code must be at least 2 characters.", "Error message is not correct");
    }

    @Test(description = "Verify that a project with the Code used in another project cant be created")
    public void checkErrorWithTheSameCode() {
        String code = "code";
        NewProjectUI project1 = NewProjectUI.builder()
                .title(name + random.nextInt(1000))
                .code(code)
                .description(name + random.nextInt(1000))
                .build();

        NewProjectUI project2 = NewProjectUI.builder()
                .title(name + random.nextInt(1000))
                .code(code)
                .description(name + random.nextInt(1000))
                .build();

        loginSteps.validLogin(USERNAME, PASSWORD);
        projectsSteps.clickCreateNewProject();
        createProjectSteps.populateNewProjectFormFull(project1);
        projectsSteps.openProjectsPage().clickCreateNewProject();
        String alertMessage = createProjectSteps.populateNewProjectFormFull(project2).checkSameCodeMessage(project2);
        Assert.assertEquals(alertMessage, "Project with the same code already exists.", "Alert message is not correct");
    }
}
