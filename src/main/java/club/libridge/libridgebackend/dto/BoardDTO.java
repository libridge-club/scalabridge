package club.libridge.libridgebackend.dto;

import java.math.BigInteger;
import java.util.EnumMap;
import java.util.Map;

import club.libridge.libridgebackend.core.Board;
import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.core.PavlicekNumber;
import club.libridge.libridgebackend.core.Strain;

public class BoardDTO {
    private static PavlicekNumber pavlicekNumberTransformer;

    static {
        pavlicekNumberTransformer = new PavlicekNumber();
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
        this.doubleDummyTable = new EnumMap<Direction, Map<Strain, Integer>>(Direction.class);
        for (Direction direction : Direction.values()) {
            EnumMap<Strain, Integer> strainMap = new EnumMap<Strain, Integer>(Strain.class);
            for (Strain strain : Strain.values()) {
                strainMap.put(strain, 6);
            }
            this.doubleDummyTable.put(direction, strainMap);
        }
    }

}
