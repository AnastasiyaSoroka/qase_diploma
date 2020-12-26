package adapters;

import io.restassured.response.ValidatableResponse;
import models.Project;

public class ProjectsAdapter extends BaseAdapter {

    public static final String URI = "v1/project";

    public ValidatableResponse post(Project project) {
        return
                post(URI, converter.toJson(project));

    }

    public ValidatableResponse get() {
        return
                get(URI);
    }

    public ValidatableResponse getSingle(String projectCode) {
        return
                get(String.format("%s/%s", URI, projectCode));
    }
}
