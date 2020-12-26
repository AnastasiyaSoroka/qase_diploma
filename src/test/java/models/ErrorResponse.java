package models;

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
public class ErrorResponse {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;
    @SerializedName("errorFields")
    @Expose
    private List<ErrorField> errorFields = null;
}
