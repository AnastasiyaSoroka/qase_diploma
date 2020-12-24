package adapters;

import models.Project;

public class ProjectsAdapter extends BaseAdapter {

    public static final String URI = "v1/project";

    public String create(Project project) {
        return
                post(URI, converter.toJson(project))
                        .body()
                        .path("result.code");

    }
}
