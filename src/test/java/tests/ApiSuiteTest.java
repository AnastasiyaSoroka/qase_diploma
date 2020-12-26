package tests;

import adapters.ProjectsAdapter;
import adapters.TestSuiteAdapter;
import io.qameta.allure.Feature;
import io.restassured.response.ValidatableResponse;
import lombok.extern.log4j.Log4j2;
import models.ErrorResponse;
import models.Project;
import models.Response;
import models.TestSuite;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.RandomString;
import utils.TestListener;

@Feature("Suites")
@Log4j2
@Listeners(TestListener.class)
public class ApiSuiteTest {
    ProjectsAdapter projectsAdapter = new ProjectsAdapter();
    TestSuiteAdapter suiteAdapter = new TestSuiteAdapter();
    RandomString randomString = new RandomString();

    String code;
    Project project;
    TestSuite suite;

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

    @Test(description = "API: Check suite created")
    public void testPostSuite() {

        suite = TestSuite.builder()
                .title(randomString.StringRandom(4))
                .description(randomString.StringRandom(4))
                .preconditions(randomString.StringRandom(4))
                .build();

        ValidatableResponse response = suiteAdapter.post(project, suite);
        boolean actualStatus = response.extract().body().as(Response.class).getStatus();
        Assert.assertTrue(actualStatus, "Status is not correct");
    }

    @Test(description = "API: Check suite can't be created without title")
    public void testPostSuiteErrorMessage() {

        suite = TestSuite.builder()
                .description(randomString.StringRandom(4))
                .preconditions(randomString.StringRandom(4))
                .build();

        ValidatableResponse response = suiteAdapter.post(project, suite);

        String errorMessage = response.extract().body().as(ErrorResponse.class).getErrorMessage();
        Assert.assertEquals(errorMessage, "Unable to create test suite. Internal error.", "Error message is not correct");
    }

    @Test(description = "API: Get a specific suite")
    public void testGetSuite() {

        suite = TestSuite.builder()
                .title(randomString.StringRandom(4))
                .description(randomString.StringRandom(4))
                .preconditions(randomString.StringRandom(4))
                .build();

        ValidatableResponse response = suiteAdapter.post(project, suite);
        int id = response.extract().body().as(Response.class).getResult().getId();
        response = suiteAdapter.getSingle(code, id);
        boolean actualStatus = response.extract().body().as(Response.class).getStatus();
        Assert.assertTrue(actualStatus, "Status is not correct");
    }

    @Test(description = "API: Get all suites from the project")
    public void testGetAllSuite() {

        suite = TestSuite.builder()
                .title(randomString.StringRandom(4))
                .description(randomString.StringRandom(4))
                .preconditions(randomString.StringRandom(4))
                .build();

        suiteAdapter.post(project, suite);

        suite = TestSuite.builder()
                .title(randomString.StringRandom(4))
                .description(randomString.StringRandom(4))
                .preconditions(randomString.StringRandom(4))
                .build();

        suiteAdapter.post(project, suite);

        ValidatableResponse response = suiteAdapter.getAll(project.getCode());
        int counter = response.extract().body().as(Response.class).getResult().getCount();
        Assert.assertEquals(counter, 2, "Number of suites is not correct");
    }

    @Test(description = "API: Check that suite can be updated")
    public void testPatchSuite() {
        String titleDefault = randomString.StringRandom(4);
        suite = TestSuite.builder()
                .title(titleDefault)
                .description(randomString.StringRandom(4))
                .preconditions(randomString.StringRandom(4))
                .build();

        ValidatableResponse response = suiteAdapter.post(project, suite).statusCode(200);
        int id = response.extract().body().as(Response.class).getResult().getId();

        suite = TestSuite.builder()
                .title(randomString.StringRandom(4))
                .build();

        suiteAdapter.patch(project, id, suite).statusCode(200);
        response = suiteAdapter.getAll(code);
        String titleActual = response.extract().body().as(Response.class).getResult().getEntities().get(0).getTitle();
        Assert.assertNotEquals(titleDefault, titleActual, "Title is not updated");
    }

    @Test(description = "API: Delete a specific suite from the project")
    public void testDeleteSuite() {

        suite = TestSuite.builder()
                .title(randomString.StringRandom(4))
                .description(randomString.StringRandom(4))
                .preconditions(randomString.StringRandom(4))
                .build();

        ValidatableResponse response = suiteAdapter.post(project, suite);
        int id = response.extract().body().as(Response.class).getResult().getId();
        response = suiteAdapter.delete(code, id);
        boolean actualStatus = response.extract().body().as(Response.class).getStatus();
        Assert.assertTrue(actualStatus, "Status is not correct");
    }

}
