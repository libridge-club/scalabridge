package club.libridge.libridgebackend.core;

import java.util.HashMap;
import java.util.Map;

public enum OddTricks {

    ONE("One", "1", 1), TWO("Two", "2", 2), THREE("Three", "3", 3), FOUR("Four", "4", 4), FIVE("Five", "5", 5), SIX("Six", "6", 6),
    SEVEN("Seven", "7", 7);

    private final String name;
    private final String symbol;
    private final int level;

    private static Map<Integer, OddTricks> mapFromLevel = new HashMap<Integer, OddTricks>();
    // Static initialization block to avoid doing this calculation every time
    static {
        for (OddTricks oddTrick : OddTricks.values()) {
            mapFromLevel.put(oddTrick.getLevel(), oddTrick);
        }
    }

    OddTricks(String name, String symbol, int level) {
        this.name = name;
        this.symbol = symbol;
        this.level = level;
    }

    public String getName() {
        return this.name;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public int getLevel() {
        return this.level;
    }

    public static OddTricks fromLevel(int level) {
        if (level < 1 || level > 7) {
            throw new IllegalArgumentException("Level should be between 1 and 7 inclusive.");
        }
        return OddTricks.mapFromLevel.get(Integer.valueOf(level));
    }

}
