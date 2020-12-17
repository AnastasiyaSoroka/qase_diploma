package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class NewProjectUI {
    String title;
    String code;
    String description;
}
