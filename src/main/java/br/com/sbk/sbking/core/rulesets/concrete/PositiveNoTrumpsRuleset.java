package br.com.sbk.sbking.core.rulesets.concrete;

import br.com.sbk.sbking.core.rulesets.implementations.DefaultSuitFollowable;
import br.com.sbk.sbking.core.rulesets.implementations.NoTrumpSuitWinnable;

public class PositiveNoTrumpsRuleset extends PositiveRuleset {

    public PositiveNoTrumpsRuleset() {
        super();
        this.suitFollowable = new DefaultSuitFollowable();
        this.winnable = new NoTrumpSuitWinnable();
    }

}
