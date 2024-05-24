package club.libridge.libridgebackend.core;

import lombok.NonNull;

public final class PlasticBoard {

    public static Direction getDealerFromBoardNumber(@NonNull BoardNumber number) {
        int mod = number.getNumber() % 4;
        return Direction.WEST.next(mod);
    }

}
