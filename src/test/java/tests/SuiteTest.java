package tests;

import models.Project;
import models.Suite;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RandomString;

public class SuiteTest extends BaseTest {

    RandomString randomString = new RandomString();

    @Test(description = "Verify that New Suite was created")
    public void checkNewSuiteCreated() {

        Project project = Project.builder()
                .title(randomString.StringRandom(4))
                .code(randomString.StringRandom(4))
                .description(randomString.StringRandom(4))
                .build();

        Suite suite = Suite.builder()
                .title(randomString.StringRandom(4))
                .description(randomString.StringRandom(4))
                .preconditions(randomString.StringRandom(4))
                .build();

        loginSteps.validLogin(USERNAME, PASSWORD);
        projectsSteps.clickCreateNewProject();
        createProjectSteps.populateNewProjectFormFull(project);
        projectSteps.clickCreateNewSuite();
        createSuiteSteps.populateNewSuiteFormFull(suite);
        Assert.assertEquals(projectSteps.getSuiteName(suite), suite.getTitle(), "Suite name does not match to expected");
    }

}
