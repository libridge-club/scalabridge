package club.libridge.libridgebackend.dto;

import java.math.BigInteger;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import club.libridge.libridgebackend.core.Board;
import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.core.NumberOfTricks;
import club.libridge.libridgebackend.core.PavlicekNumber;
import club.libridge.libridgebackend.core.Strain;
import club.libridge.libridgebackend.dds.DoubleDummyTable;

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
    }

    public BoardDTO(String pavlicekNumber) {
        this.board = pavlicekNumberTransformer.getBoardFromNumber(new BigInteger(pavlicekNumber));
        this.pavlicekNumber = pavlicekNumber;
    }

    public BoardDTO(Board board, String pavlicekNumber) {
        this.board = board;
        this.pavlicekNumber = pavlicekNumber;
    }

    public void setDoubleDummyTable(DoubleDummyTable doubleDummyTable) {
        this.doubleDummyTable = new EnumMap<Direction, Map<Strain, Integer>>(Direction.class);
        for (Direction direction : Direction.values()) {
            EnumMap<Strain, Integer> strainMap = new EnumMap<Strain, Integer>(Strain.class);
            for (Strain strain : Strain.values()) {
                NumberOfTricks tricksAvailableFor = doubleDummyTable.getTricksAvailableFor(strain, direction);
                strainMap.put(strain, tricksAvailableFor.getInt());
            }
            this.doubleDummyTable.put(direction, strainMap);
        }
        this.doubleDummyTable = Collections.unmodifiableMap(this.doubleDummyTable);
    }

}
