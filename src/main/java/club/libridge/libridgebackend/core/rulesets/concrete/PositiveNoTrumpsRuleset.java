package club.libridge.libridgebackend.core.rulesets.concrete;

import club.libridge.libridgebackend.core.rulesets.implementations.DefaultSuitFollowable;
import club.libridge.libridgebackend.core.rulesets.implementations.NoTrumpSuitWinnable;

public class PositiveNoTrumpsRuleset extends PositiveRuleset {

    public PositiveNoTrumpsRuleset() {
        super();
        this.suitFollowable = new DefaultSuitFollowable();
        this.winnable = new NoTrumpSuitWinnable();
    }

}
