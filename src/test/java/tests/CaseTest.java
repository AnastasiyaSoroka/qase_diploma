package tests;

import models.Case;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class CaseTest extends BaseTest {
    Random random = new Random();
    String name = "n";

    @Test(description = "Verify that New Case was created")
    public void checkNewProjectCreated() {

        Project project = Project.builder()
                .title(name + random.nextInt(1000))
                .code(name + random.nextInt(1000))
                .description(name + random.nextInt(1000))
                .build();

        Case caseModel = Case.builder()
                .title(name + random.nextInt(1000))
                .status("Actual")
                .description("asdasdasd dfyg")
                .severity("Blocker")
                .priority("High")
                .type("Smoke")
                .behavior("Positive")
                .automation("Automated")
                .build();

        loginSteps.validLogin(USERNAME, PASSWORD);
        projectsSteps.clickCreateNewProject();
        createProjectSteps.populateNewProjectFormFull(project);
        projectSteps.clickCreateNewCase();
        createCaseSteps.populateNewSuiteFormFull(caseModel);
        Assert.assertEquals(projectSteps.getCaseName(caseModel), caseModel.getTitle(), "Case name does not match to expected");
    }

}
