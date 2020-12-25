package models;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProjectResponse {
    @Expose
    private Boolean status;
//    @Expose
//    private Result result;
}
