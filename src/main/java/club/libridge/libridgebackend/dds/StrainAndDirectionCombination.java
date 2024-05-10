package club.libridge.libridgebackend.dds;

import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.core.Strain;

public class StrainAndDirectionCombination {

    private Strain strain;
    private Direction direction;

    /**
     * @deprecated Spring eyes only
     */
    @Deprecated
    private StrainAndDirectionCombination() {
        super();
    }

    public StrainAndDirectionCombination(Strain strain, Direction direction) {
        this.strain = strain;
        this.direction = direction;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((strain == null) ? 0 : strain.hashCode());
        result = prime * result + ((direction == null) ? 0 : direction.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        StrainAndDirectionCombination other = (StrainAndDirectionCombination) obj;
        if (strain != other.strain) {
            return false;
        }
        if (direction != other.direction) {
            return false;
        }
        return true;
    }

}
