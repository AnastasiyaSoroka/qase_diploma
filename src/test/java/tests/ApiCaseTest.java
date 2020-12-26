package tests;

import adapters.TestCaseAdapter;
import adapters.ProjectsAdapter;
import io.qameta.allure.Feature;
import io.restassured.response.ValidatableResponse;
import lombok.extern.log4j.Log4j2;
import models.TestCase;
import models.Project;
import models.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.RandomString;
import utils.TestListener;

@Feature("API")
@Log4j2
@Listeners(TestListener.class)
public class ApiCaseTest {
    ProjectsAdapter projectsAdapter = new ProjectsAdapter();
    TestCaseAdapter caseAdapter = new TestCaseAdapter();
    RandomString randomString = new RandomString();

    String code;
    Project project;
    TestCase testCase;

    @BeforeMethod
    public void initProject() {
        code = randomString.StringRandom(4);
        project = Project.builder()
                .title(randomString.StringRandom(4))
                .access("all")
                .code(code)
                .group("111")
                .description(randomString.StringRandom(4) + " " + randomString.StringRandom(4))
                .build();

        projectsAdapter.post(project).statusCode(200);
    }

    @Test(description = "API: Check test case created")
    public void testPostTestCase() {

        testCase = TestCase.builder()
                .title(randomString.StringRandom(4))
                .build();

        ValidatableResponse response = caseAdapter.post(project, testCase);
        boolean actualStatus = response.extract().body().as(Response.class).getStatus();
        Assert.assertTrue(actualStatus, "Status is not correct");
    }

}
