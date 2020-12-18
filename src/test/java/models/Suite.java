package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Suite {
    @Expose
    private String title;
    @SerializedName("parent_id")
    @Expose
    private Object parentId;
    @Expose
    private String description;
    @Expose
    private String preconditions;
}
