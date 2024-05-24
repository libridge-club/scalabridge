package club.libridge.libridgebackend.core.rulesets.concrete;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import club.libridge.libridgebackend.core.comparators.CardInsideHandComparator;

public class PositiveNoTrumpsRulesetTest {

    private PositiveNoTrumpsRuleset positiveNoTrumpsRuleset;

    @BeforeEach
    public void createPositiveNoTrumpsRuleset() {
        this.positiveNoTrumpsRuleset = new PositiveNoTrumpsRuleset();
    }

    @Test
    public void shouldGetComparator() {
        assertTrue(this.positiveNoTrumpsRuleset.getCardComparator() instanceof CardInsideHandComparator);
    }

}
