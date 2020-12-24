package tests;

import adapters.BaseAdapter;
import adapters.ProjectsAdapter;
import io.restassured.response.ValidatableResponse;
import models.Project;
import models.ProjectResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class ProjectTestsApi {
    Random random = new Random();
    String name = "n";
    BaseAdapter baseAdapter = new BaseAdapter();

    @Test
    public void testGet() {
        ValidatableResponse response = baseAdapter.get("v1/project").statusCode(200);

        System.out.println(response.extract().body().path("status").toString());
        System.out.println(response.extract().body().as(ProjectResponse.class).getStatus());

        //  Assert.assertTrue(response.extract().body().as(ProjectResponse.class).isStatus(), "Status is not correct");
        //  new BaseAdapter().get("v1/project");
    }


}
