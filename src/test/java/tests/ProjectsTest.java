package tests;

import models.Project;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class ProjectsTest extends BaseTest {

    Random random = new Random();
    String name = "n";

    @Test(description = "Verify that New project was created")
    public void checkNewProjectCreated() {
        Project model = Project.builder()
                .title(name + random.nextInt(1000))
                .code(name + random.nextInt(1000))
                .description(name + random.nextInt(1000))
                .build();

        loginSteps.validLogin(USERNAME, PASSWORD);
        projectsSteps.clickCreateNewProject();
        createProjectSteps.populateNewProjectFormFull(model);
        Assert.assertEquals(projectSteps.getProjectName(model.getCode()), model.getTitle(), "Project name does not match to expected");
    }

    @Test(description = "Verify that Code value cant be shorter than 2 characters")
    public void checkErrorWithShortCode() {
        Project model = Project.builder()
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
        String code = name + random.nextInt(10);
        Project project1 = Project.builder()
                .title(name + random.nextInt(1000))
                .code(code)
                .description(name + random.nextInt(1000))
                .build();

        Project project2 = Project.builder()
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
