package club.libridge.libridgebackend.dds;

import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.core.Strain;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@AllArgsConstructor
@EqualsAndHashCode
public class StrainAndDirectionCombination {

    @NonNull
    private Strain strain;
    @NonNull
    private Direction direction;

}
