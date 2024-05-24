package club.libridge.libridgebackend.dto;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

import club.libridge.libridgebackend.core.Board;
import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.core.NumberOfTricks;
import club.libridge.libridgebackend.core.Strain;
import club.libridge.libridgebackend.dds.DoubleDummyTable;
import lombok.Setter;

public class BoardDTO {

    @Setter
    private UUID id;

    @SuppressWarnings("unused") // Spring serializes this in http responses.
    private final Board board;
    @SuppressWarnings("unused") // Spring serializes this in http responses.
    private final String pavlicekNumber;
    private Map<Direction, Map<Strain, Integer>> doubleDummyTable;

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
            this.doubleDummyTable.put(direction, Collections.unmodifiableMap(strainMap));
        }
        this.doubleDummyTable = Collections.unmodifiableMap(this.doubleDummyTable);
    }

}
