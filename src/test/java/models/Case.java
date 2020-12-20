package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Case {
    String title;
    String status;
    String description;
    String suite;
    String severity;
    String priority;
    String type;
    String milestone;
    String behavior;
    String automation;
    String preconds;
    String postcond;
}
