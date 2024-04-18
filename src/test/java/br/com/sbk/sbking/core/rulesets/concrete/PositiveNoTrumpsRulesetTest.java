package br.com.sbk.sbking.core.rulesets.concrete;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.sbk.sbking.core.comparators.CardInsideHandComparator;

public class PositiveNoTrumpsRulesetTest {

    private static final int POSITIVE_NO_TRUMPS_SCORE_MULTIPLIER = 25;
    private static final int POSITIVE_POINTS_PER_TRICK = 1;
    private static final String POSITIVE_NO_TRUMPS_SHORT_DESCRIPTION = "NT";
    private static final String POSITIVE_NO_TRUMPS_COMPLETE_DESCRIPTION = "Make the most tricks without a trump suit";
    private PositiveNoTrumpsRuleset positiveNoTrumpsRuleset;

    @BeforeEach
    public void createPositiveNoTrumpsRuleset() {
        this.positiveNoTrumpsRuleset = new PositiveNoTrumpsRuleset();
    }

    @Test
    public void shouldGetComparator() {
        assertTrue(this.positiveNoTrumpsRuleset.getComparator() instanceof CardInsideHandComparator);
    }

}
