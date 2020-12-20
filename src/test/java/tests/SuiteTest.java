package tests;

import models.Project;
import models.Suite;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class SuiteTest extends BaseTest {

    Random random = new Random();
    String name = "n";

    @Test(description = "Verify that New Suite was created")
    public void checkNewSuiteCreated() {

        Project project = Project.builder()
                .title(name + random.nextInt(1000))
                .code(name + random.nextInt(1000))
                .description(name + random.nextInt(1000))
                .build();

        Suite suite = Suite.builder()
                .title(name + random.nextInt(1000))
                .description(name + random.nextInt(1000))
                .preconditions(name + random.nextInt(1000))
                .build();

        loginSteps.validLogin(USERNAME, PASSWORD);
        projectsSteps.clickCreateNewProject();
        createProjectSteps.populateNewProjectFormFull(project);
        projectSteps.clickCreateNewSuite();
        createSuiteSteps.populateNewSuiteFormFull(suite);
        projectSteps.getSuiteName(suite);
        Assert.assertEquals(projectSteps.getSuiteName(suite), suite.getTitle(), "Suite name does not match to expected");
    }

}
