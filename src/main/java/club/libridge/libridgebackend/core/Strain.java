package club.libridge.libridgebackend.core;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import club.libridge.libridgebackend.core.rulesets.concrete.PositiveNoTrumpsRuleset;
import club.libridge.libridgebackend.core.rulesets.concrete.PositiveRuleset;
import club.libridge.libridgebackend.core.rulesets.concrete.PositiveWithTrumpsRuleset;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

/**
 * The Laws of Bridge use "Denomination" but we will use Strain as it is more
 * common.
 */
@AllArgsConstructor
@Getter
public enum Strain {
    CLUBS("Clubs", new PositiveWithTrumpsRuleset(Suit.CLUBS)), DIAMONDS("Diamonds", new PositiveWithTrumpsRuleset(Suit.DIAMONDS)),
    HEARTS("Hearts", new PositiveWithTrumpsRuleset(Suit.HEARTS)), SPADES("Spades", new PositiveWithTrumpsRuleset(Suit.SPADES)),
    NOTRUMPS("No Trumps", new PositiveNoTrumpsRuleset());

    private final String name;
    private final PositiveRuleset positiveRuleset;

    public String getSymbol() {
        return String.valueOf(this.name.charAt(0));
    }

    private static final Map<String, Strain> MAP_FROM_NAME;
    private static final Map<Suit, Strain> MAP_FROM_SUIT;

    static {
        MAP_FROM_NAME = new HashMap<String, Strain>();
        for (Strain strain : Strain.values()) {
            MAP_FROM_NAME.put(strain.getName(), strain);
        }

        MAP_FROM_SUIT = new EnumMap<Suit, Strain>(Suit.class);
        MAP_FROM_SUIT.put(Suit.CLUBS, CLUBS);
        MAP_FROM_SUIT.put(Suit.DIAMONDS, DIAMONDS);
        MAP_FROM_SUIT.put(Suit.HEARTS, HEARTS);
        MAP_FROM_SUIT.put(Suit.SPADES, SPADES);
    }

    public static Strain fromName(@NonNull String name) {
        return Strain.MAP_FROM_NAME.get(name);
    }

    public static Strain fromSuit(@NonNull Suit suit) {
        return Strain.MAP_FROM_SUIT.get(suit);
    }

}
