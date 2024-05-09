package club.libridge.libridgebackend.dds;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import club.libridge.libridgebackend.core.Board;
import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.core.boarddealer.Complete52CardDeck;
import club.libridge.libridgebackend.core.boarddealer.ShuffledBoardDealerWithSeed;
import club.libridge.libridgedds.jna.DDSWrapper;

public class DoubleDummySolverTest {

    private DoubleDummySolver subject;
    private static DDSWrapper ddsWrapper;

    @BeforeAll
    public static void createWrapper(){ /* Avoid recreating the wrapper many times */
        ddsWrapper = new DDSWrapper();
    }

    @BeforeEach
    public void setup(){
        this.subject = new DoubleDummySolver(DoubleDummySolverTest.ddsWrapper);
    }

    @Test
    public void shouldFindAndRunLibridgeDDS(){
        long seed = 123L;
        ShuffledBoardDealerWithSeed shuffledBoardDealerWithSeed = new ShuffledBoardDealerWithSeed(seed);
        Board boardWithSeed = shuffledBoardDealerWithSeed.dealBoard(Direction.NORTH, new Complete52CardDeck().getDeck());
        List<Integer> expectedResponse = List.of(11, 1, 11, 1, 7, 5, 7, 5, 5, 8, 5, 8, 9, 4, 9, 4, 7, 4, 7, 4 );

        DoubleDummyTable doubleDummyTable = this.subject.calculateDoubleDummyTable(boardWithSeed);
        
        List<Integer> actualResponse = doubleDummyTable.toDDSIntegerList();
        assertEquals(expectedResponse, actualResponse);
    }

}
