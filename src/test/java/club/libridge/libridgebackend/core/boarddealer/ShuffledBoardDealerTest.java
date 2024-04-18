package club.libridge.libridgebackend.core.boarddealer;

import static club.libridge.libridgebackend.core.GameConstants.SIZE_OF_HAND;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import club.libridge.libridgebackend.core.Board;
import club.libridge.libridgebackend.core.Direction;

public class ShuffledBoardDealerTest {

    // FIXME This is an integration test as it needs other classes to work.
    @Test
    public void shouldReceiveABoardWithTheCorrectDealerAndACompleteSetOfCards() {
        Direction dealer = Direction.NORTH;
        BoardDealer boardDealer = new ShuffledBoardDealer();
        CardDeck anyCardDeck = new Complete52CardDeck();
        Board board = boardDealer.dealBoard(dealer, anyCardDeck.getDeck());

        // The correct test should verify if new Board(hands, dealer)
        // was called but Mockito can't do that.
        // Coupling this test with Hand and Board instead :(
        for (Direction direction : Direction.values()) {
            assertEquals(SIZE_OF_HAND, board.getHandOf(direction).size());
        }
        assertEquals(dealer, board.getDealer());

    }

}
