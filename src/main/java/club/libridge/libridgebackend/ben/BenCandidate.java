package club.libridge.libridgebackend.ben;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BenCandidate {
    private String call;
    @JsonProperty("insta_score")
    private Double instaScore;
    @JsonProperty("expected_score")
    private Double expectedScore;
    private Integer adjustment;
}
