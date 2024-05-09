package club.libridge.libridgebackend.dds;

import club.libridge.libridgebackend.core.Board;
import club.libridge.libridgebackend.pbn.PBNUtils;
import club.libridge.libridgedds.jna.DDSWrapper;

public final class DoubleDummySolver {

    private DDSWrapper ddsWrapper;

    public DoubleDummySolver(DDSWrapper ddsWrapper) {
        this.ddsWrapper = ddsWrapper;
    }

    public DoubleDummyTable calculateDoubleDummyTable(Board board) {
        String pbnString = PBNUtils.dealTagStringFromBoard(board);
        return new DoubleDummyTable(this.ddsWrapper.calcDDtablePBN(pbnString));
    }

}
