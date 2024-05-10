package club.libridge.libridgebackend.dds;

import java.util.List;

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
        return this.calculateDoubleDummyTable(pbnString);
    }

    public DoubleDummyTable calculateDoubleDummyTable(String pbnString) {
        return new DoubleDummyTable(this.calcDDtablePBN(pbnString));
    }

    public List<Integer> calcDDtablePBN(String pbnString) {
        return this.ddsWrapper.calcDDtablePBN(pbnString);
    }

}
