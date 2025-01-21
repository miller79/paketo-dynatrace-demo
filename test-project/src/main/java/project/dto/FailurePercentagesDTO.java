package project.dto;

import lombok.Data;

@Data
public class FailurePercentagesDTO {
    private int failurePercentage;
    private int unavailablePercentage;
    private boolean delay;
}
