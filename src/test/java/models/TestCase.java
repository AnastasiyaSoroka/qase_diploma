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
public class TestCase {
    @SerializedName("title")
    @Expose
    private String title;
    private String status;
    private String description;
    private String suite;
    private String severity;
    private String priority;
    private String type;
    private String milestone;
    private String behavior;
    private String automation;
    private String preconds;
    private String postcond;

}
