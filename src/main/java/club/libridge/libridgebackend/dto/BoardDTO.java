package club.libridge.libridgebackend.dto;

import java.math.BigInteger;
import java.util.Map;

import club.libridge.libridgebackend.core.Board;
import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.core.PavlicekNumber;
import club.libridge.libridgebackend.core.Strain;
import club.libridge.libridgebackend.dds.DoubleDummySolver;
import club.libridge.libridgedds.jna.DDSWrapper;

public class BoardDTO {
    private static PavlicekNumber pavlicekNumberTransformer;
    private static DoubleDummySolver doubleDummySolver;

    static {
        pavlicekNumberTransformer = new PavlicekNumber();
        doubleDummySolver = new DoubleDummySolver(new DDSWrapper());
    }

    private Board board;
    private String pavlicekNumber;
    private Map<Direction, Map<Strain, Integer>> doubleDummyTable;

    /**
     * @deprecated Spring eyes only
     */
    @Deprecated
    private BoardDTO() {
    }

    public BoardDTO(Board board) {
        this.pavlicekNumber = pavlicekNumberTransformer.getNumberFromBoard(board).toString();
        this.board = board;
        this.calculateDoubleDummyTable();
    }

    public BoardDTO(String pavlicekNumber) {
        this.board = pavlicekNumberTransformer.getBoardFromNumber(new BigInteger(pavlicekNumber));
        this.pavlicekNumber = pavlicekNumber;
        this.calculateDoubleDummyTable();
    }

    private void calculateDoubleDummyTable() {
        this.doubleDummyTable = doubleDummySolver.calculateDoubleDummyTable(this.board).getMapFormatted();
    }

}
