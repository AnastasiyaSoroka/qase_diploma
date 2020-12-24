package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Defects {
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("open")
    @Expose
    private Integer open;
}
