package API.entities;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class StepEntity {

    private Long id;

    private Long playGroundId;
    private Long playGroundKey;
    private Long playerId;
    private Long playerKey;
    private int cell;
    private int stepNo;
    private char sign;

}
