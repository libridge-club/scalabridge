package club.libridge.libridgebackend.core;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper=false)
public class Bid extends Call implements Comparable<Bid> {

    @NonNull private final OddTricks oddTricks;
    @NonNull private final Strain strain;

    /**
     * From the Laws of Bridge 2017:
     * A bid supersedes a previous bid if it designates
     * either the same number of odd tricks in a higher-ranking denomination
     * or a greater number of odd tricks in any denomination.
     */
    @Override
    public int compareTo(Bid o) {
        int oddTricksComparation = this.getOddTricks().compareTo(o.getOddTricks());
        if (oddTricksComparation != 0) {
            return oddTricksComparation;
        }
        return this.getStrain().compareTo(o.getStrain());
    }

}
