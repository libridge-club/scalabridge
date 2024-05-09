package club.libridge.libridgebackend.dds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.core.NumberOfTricks;
import club.libridge.libridgebackend.core.Strain;

public class DoubleDummyTable {

    /**
     * The String key is coded as follows:
     * the first character represents the Strain (N, S, H, D, C)
     * the second character represents the Direction (N, E, S, W)
     */
    private Map<String, NumberOfTricks> tricksAvailable;
    private static final List<Strain> STRAIN_ORDER_FROM_DDS;
    private static final List<Direction> DIRECTION_ORDER_FROM_DDS;

    static {
        STRAIN_ORDER_FROM_DDS = List.of(Strain.NOTRUMPS, Strain.SPADES, Strain.HEARTS, Strain.DIAMONDS, Strain.CLUBS);
        DIRECTION_ORDER_FROM_DDS = List.of(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);
    }

    private static String getStringKey(Strain strain, Direction direction) {
        StringBuilder response = new StringBuilder();
        response.append(strain.getSymbol());
        response.append(direction.getAbbreviation());
        return response.toString();
    }

    /**
     * From DDS documentation:
     * Encodes the solution of a deal for combinations of denomination and declarer.
     * First index is denomination. Suit encoding.
     * Second index is declarer.  Hand encoding.
     * Each entry is a number of tricks.
     */
    public DoubleDummyTable(List<Integer> list) { // This is the format received from DDS
        this.tricksAvailable = new HashMap<>();

        int currentIndex = 0;
        for (Strain strain : STRAIN_ORDER_FROM_DDS) {
            for (Direction direction : DIRECTION_ORDER_FROM_DDS) {
                String stringKey = getStringKey(strain, direction);
                this.tricksAvailable.put(stringKey, new NumberOfTricks(list.get(currentIndex)));
                currentIndex++;
            }
        }
    }

    public NumberOfTricks getTricksAvailableFor(Strain strain, Direction direction) {
        return this.tricksAvailable.get(getStringKey(strain, direction));
    }

    public List<Integer> toDDSIntegerList() {
        ArrayList<Integer> returnValue = new ArrayList<Integer>();
        for (Strain strain : STRAIN_ORDER_FROM_DDS) {
            for (Direction direction : DIRECTION_ORDER_FROM_DDS) {
                String stringKey = getStringKey(strain, direction);
                NumberOfTricks numberOfTricks = this.tricksAvailable.get(stringKey);
                returnValue.add(numberOfTricks.getInt());
            }
        }
        return returnValue;
    }

}
