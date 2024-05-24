package club.libridge.libridgebackend.core;

import java.util.HashMap;
import java.util.Map;

import club.libridge.libridgebackend.core.rulesets.concrete.PositiveNoTrumpsRuleset;
import club.libridge.libridgebackend.core.rulesets.concrete.PositiveRuleset;
import club.libridge.libridgebackend.core.rulesets.concrete.PositiveWithTrumpsRuleset;
import lombok.AllArgsConstructor;
import lombok.Getter;

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

    private static Map<String, Strain> mapFromName = new HashMap<String, Strain>();

    static {
        for (Strain strain : Strain.values()) {
            mapFromName.put(strain.getName(), strain);
        }
    }

    public static Strain fromName(String name) {
        return Strain.mapFromName.get(name);
    }

}
