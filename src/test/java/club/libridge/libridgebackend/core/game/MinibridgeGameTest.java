package club.libridge.libridgebackend.core.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Deque;

import org.junit.jupiter.api.Test;

import club.libridge.libridgebackend.core.Card;
import club.libridge.libridgebackend.core.Direction;

public class MinibridgeGameTest {

    @Test
    public void getLeaderShouldReturnTheNextDirectionFromDealer() {
        @SuppressWarnings("unchecked")
        Deque<Card> deck = mock(Deque.class);
        MinibridgeGame minibridgeGame = new MinibridgeGame(deck);
        Direction dealer = minibridgeGame.getDealer();
        assertEquals(dealer.next(), minibridgeGame.getLeader());
    }

}
