package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("filtered")
    @Expose
    private Integer filtered;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("entities")
    @Expose
    private List<Entity> entities = null;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("counts")
    @Expose
    private Counts counts;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("preconditions")
    @Expose
    private String preconditions;
    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("cases_count")
    @Expose
    private Integer cases_count;
    @SerializedName("parent_id")
    @Expose
    private Object parent_id;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("updated")
    @Expose
    private String updated;

}
