package club.libridge.libridgebackend.core.rulesets.interfaces;

import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.core.Trick;

public interface Winnable {

    Direction getWinner(Trick trick);

}
