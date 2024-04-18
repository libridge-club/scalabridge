package br.com.sbk.sbking.core.rulesets.concrete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.sbk.sbking.core.Suit;
import br.com.sbk.sbking.core.comparators.CardInsideHandWithSuitComparator;

public class PositiveWithTrumpsRulesetTest {

    private static final int POSITIVE_WITH_TRUMPS_SCORE_MULTIPLIER = 25;
    private static final int POSITIVE_POINTS_PER_TRICK = 1;
    private static final String POSITIVE_SPADES_SHORT_DESCRIPTION = "\u2660";
    private static final String POSITIVE_SPADES_COMPLETE_DESCRIPTION = "Make the most tricks with spades as trump suit";
    private PositiveWithTrumpsRuleset positiveWithTrumpRuleset;
    private Suit trumpSuit;

    @BeforeEach
    public void createPositiveNoTrumpsRuleset() {
        trumpSuit = Suit.SPADES;
        this.positiveWithTrumpRuleset = new PositiveWithTrumpsRuleset(trumpSuit);
    }

    @Test
    public void shouldGetTrumpSuit() {
        assertEquals(trumpSuit, this.positiveWithTrumpRuleset.getTrumpSuit());
    }

    @Test
    public void shouldGetComparator() {
        assertEquals(trumpSuit, this.positiveWithTrumpRuleset.getTrumpSuit());
        assertTrue(this.positiveWithTrumpRuleset.getComparator() instanceof CardInsideHandWithSuitComparator);
    }

}
