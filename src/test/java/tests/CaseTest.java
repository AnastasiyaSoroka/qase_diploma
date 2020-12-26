package tests;

import adapters.ProjectsAdapter;
import io.qameta.allure.Feature;
import models.TestCase;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.RandomString;

@Feature("TestCases")
public class CaseTest extends BaseTest {

    RandomString randomString = new RandomString();
    Project project;
    TestCase caseModel;
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

    @Test(description = "Verify that New Case was created")
    public void checkNewProjectCreated() {
        caseModel = TestCase.builder()
                .title(randomString.StringRandom(4))
                .status("Actual")
                .description("asdasdasd dfyg")
                .severity("Blocker")
                .priority("High")
                .type("Smoke")
                .behavior("Positive")
                .automation("Automated")
                .build();

        projectSteps.clickCreateNewCase();
        createCaseSteps.populateNewSuiteFormFull(caseModel);
        Assert.assertEquals(projectSteps.getCaseName(caseModel), caseModel.getTitle(), "Case name does not match to expected");
    }

}
