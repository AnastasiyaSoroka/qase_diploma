package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Counts {
    @SerializedName("cases")
    @Expose
    private Integer cases;
    @SerializedName("suites")
    @Expose
    private Integer suites;
    @SerializedName("milestones")
    @Expose
    private Integer milestones;
    @SerializedName("runs")
    @Expose
    private Runs runs;
    @SerializedName("defects")
    @Expose
    private Defects defects;
}
