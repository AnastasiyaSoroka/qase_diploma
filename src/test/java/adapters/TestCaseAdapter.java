package adapters;

import io.restassured.response.ValidatableResponse;
import models.Project;
import models.TestCase;

public class TestCaseAdapter extends BaseAdapter {
    public static final String URI = "v1/case";

    public ValidatableResponse post(Project project, TestCase testCase) {
        return
                post(String.format("%s/%s", URI, project.getCode()), converter.toJson(testCase));
    }

    public ValidatableResponse delete(String projectCode, int id) {
        return
                delete(String.format("%s/%s/%s", URI, projectCode, id));
    }

    public ValidatableResponse getAll(String projectCode) {
        return
                get(String.format("%s/%s", URI, projectCode));
    }
}
