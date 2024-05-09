package club.libridge.libridgebackend.pbn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import club.libridge.libridgebackend.core.Board;
import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.core.boarddealer.Complete52CardDeck;
import club.libridge.libridgebackend.core.boarddealer.ShuffledBoardDealer;
import club.libridge.libridgebackend.core.boarddealer.ShuffledBoardDealerWithSeed;

public class PBNUtilsTest {

    @Test
    void testPbnStringFromBoard() {
        ShuffledBoardDealer shuffledBoardDealer = new ShuffledBoardDealer();
        Board dealBoard = shuffledBoardDealer.dealBoard(Direction.NORTH, new Complete52CardDeck().getDeck());

        String response = PBNUtils.dealTagStringFromBoard(dealBoard);

        assertEquals('N', response.charAt(0));

    }
    
    @Test
    void testDealTagStringFromBoardAndDirection() {

        long seed = 123L;
        ShuffledBoardDealerWithSeed shuffledBoardDealerWithSeed = new ShuffledBoardDealerWithSeed(seed);
        Board boardWithSeed = shuffledBoardDealerWithSeed.dealBoard(Direction.NORTH, new Complete52CardDeck().getDeck());
        String expectedString = "E:86.KT2.K85.Q9742 KJT932.97.942.86 54.8653.AQJT73.3 AQ7.AQJ4.6.AKJT5";

        String response = PBNUtils.dealTagStringFromBoardAndDirection(boardWithSeed, Direction.EAST);

        assertEquals(expectedString, response);

    }
}
