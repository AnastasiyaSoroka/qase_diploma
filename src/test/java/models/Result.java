package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
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

}
