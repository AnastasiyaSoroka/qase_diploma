package tests;

import adapters.ProjectsAdapter;
import io.restassured.response.ValidatableResponse;
import models.projects.Project;
import models.ProjectResponse;
import models.ProjectsResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.RandomString;

public class ProjectTestsApi {

    ProjectsAdapter projectsAdapter = new ProjectsAdapter();
    RandomString randomString = new RandomString();

    String code;
    Project project;

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
    }

    @Test(description = "API: Check project created")
    public void testPostProject() {
        ValidatableResponse response =  new ProjectsAdapter().post(project);
        String actualCode = response.extract().body().as(ProjectResponse.class).getResult().getCode();
        Assert.assertEquals(actualCode, code, "Project Code is not correct");
    }


    @Test(description = "API: Get all projects, check that list is not empty")
    public void testGet() {
        ValidatableResponse response = projectsAdapter.get();
        boolean isListEmpty = false;
        int total = response.extract().body().as(ProjectsResponse.class).getResult().getTotal();
        if (total > 0) {
            isListEmpty = true;
        }
        Assert.assertTrue(isListEmpty, "List of projects shouldn't be empty");
    }

    @Test(description = "API: Get single project")
    public void testGetSingle() {
        new ProjectsAdapter().post(project);
        ValidatableResponse response = projectsAdapter.getSingle(code);
        String actualCode = response.extract().body().as(ProjectResponse.class).getResult().getCode();
        Assert.assertEquals(actualCode, code, "Project Code is not correct");
    }

}
