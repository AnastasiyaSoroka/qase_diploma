package tests;

import adapters.ProjectsAdapter;
import io.qameta.allure.Feature;
import models.Project;
import models.TestSuite;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.RandomString;

@Feature("Suites")
public class SuiteTest extends BaseTest {

    RandomString randomString = new RandomString();
    Project project;
    TestSuite suite;
    ProjectsAdapter projectsAdapter = new ProjectsAdapter();

    @BeforeMethod(description = "Login and Create new project")
    public void createNewProject() {
        project = Project.builder()
                .title(randomString.StringRandom(4))
                .code(randomString.StringRandom(4))
                .description(randomString.StringRandom(4))
                .build();
        projectsAdapter.post(project);
        loginSteps.validLogin(USERNAME, PASSWORD);
        projectSteps.getProjectName(project.getCode());
    }

    @Test(description = "Verify that New Suite was created")
    public void checkNewSuiteCreated() {

        suite = TestSuite.builder()
                .title(randomString.StringRandom(4))
                .description(randomString.StringRandom(4))
                .preconditions(randomString.StringRandom(4))
                .build();

        projectSteps.clickCreateNewSuite();
        createSuiteSteps.populateNewSuiteFormFull(suite);
        Assert.assertEquals(projectSteps.getSuiteName(suite), suite.getTitle(), "Suite name does not match to expected");
    }

}
