package club.libridge.libridgebackend.core.rulesets.interfaces;

import club.libridge.libridgebackend.core.Card;
import club.libridge.libridgebackend.core.Hand;
import club.libridge.libridgebackend.core.Trick;

public interface SuitFollowable {

    boolean followsSuit(Trick trick, Hand hand, Card card);

}
