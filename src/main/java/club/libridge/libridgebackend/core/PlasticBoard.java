package club.libridge.libridgebackend.core;

public final class PlasticBoard {

    public static Direction getDealerFromBoardNumber(BoardNumber number) {
        int mod = number.getNumber() % 4;
        return Direction.WEST.next(mod);
    }

}
