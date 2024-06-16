package club.libridge.libridgebackend.ben;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BenResponse {

    private String bid;
    private List<BenCandidate> candidates;

}
