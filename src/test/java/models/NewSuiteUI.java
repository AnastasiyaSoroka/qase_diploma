package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class NewSuiteUI {
    String title;
    String parentSuite;
    String description;
    String preconditions;
}
