package tests;

import adapters.ProjectsAdapter;
import io.qameta.allure.Feature;
import io.restassured.response.ValidatableResponse;
import lombok.extern.log4j.Log4j2;
import models.ErrorResponse;
import models.Project;
import models.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.RandomString;
import utils.TestListener;

@Feature("Projects")
@Log4j2
@Listeners(TestListener.class)
public class ApiProjectTest {

    ProjectsAdapter projectsAdapter = new ProjectsAdapter();
    RandomString randomString = new RandomString();

    String code;
    Project project;

    @Test(description = "API: Check project created")
    public void testPostProject() {
        code = randomString.StringRandom(4);
        project = Project.builder()
                .title(randomString.StringRandom(4))
                .access("all")
                .code(code)
                .group("111")
                .description(randomString.StringRandom(4) + " " + randomString.StringRandom(4))
                .build();

        ValidatableResponse response =  projectsAdapter.post(project);
        String actualCode = response.extract().body().as(Response.class).getResult().getCode();
        Assert.assertEquals(actualCode, code, "Project Code is not correct");
    }


    @Test(description = "API: Get all projects, check that list is not empty")
    public void testGet() {
        ValidatableResponse response = projectsAdapter.get();
        boolean isListEmpty = false;
        int total = response.extract().body().as(Response.class).getResult().getTotal();
        if (total > 0) {
            isListEmpty = true;
        }
        Assert.assertTrue(isListEmpty, "List of projects shouldn't be empty");
    }

    @Test(description = "API: Get single project")
    public void testGetSingle() {
        code = randomString.StringRandom(4);
        project = Project.builder()
                .title(randomString.StringRandom(4))
                .access("all")
                .code(code)
                .group("111")
                .description(randomString.StringRandom(4) + " " + randomString.StringRandom(4))
                .build();
        projectsAdapter.post(project).statusCode(200);
        ValidatableResponse response = projectsAdapter.getSingle(code);
        String actualCode = response.extract().body().as(Response.class).getResult().getCode();
        Assert.assertEquals(actualCode, code, "Project Code is not correct");
    }

    @Test(description = "API: Check project can't be created without title")
    public void testPostProjectErrorMessageWithoutTitle() {
        code = randomString.StringRandom(4);
        project = Project.builder()
                .access("all")
                .code(code)
                .group("111")
                .description(randomString.StringRandom(4) + " " + randomString.StringRandom(4))
                .build();

        ValidatableResponse response =  projectsAdapter.post(project);
        String errorMessage = response.extract().body().as(ErrorResponse.class).getErrorFields().get(0).getError();
        Assert.assertEquals(errorMessage, "Title is required","Error message is not correct");
    }

    @Test(description = "API: Check project can't be created without code")
    public void testPostProjectErrorMessageWithoutCode() {
        code = randomString.StringRandom(4);
        project = Project.builder()
                .title(randomString.StringRandom(4))
                .access("all")
                .group("111")
                .description(randomString.StringRandom(4) + " " + randomString.StringRandom(4))
                .build();

        ValidatableResponse response =  projectsAdapter.post(project);
        String errorMessage = response.extract().body().as(ErrorResponse.class).getErrorFields().get(0).getError();
        Assert.assertEquals(errorMessage, "Project code is required","Error message is not correct");
    }

}
