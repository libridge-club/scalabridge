package club.libridge.libridgebackend.core.boarddealer;

import java.util.Deque;

import club.libridge.libridgebackend.core.Board;
import club.libridge.libridgebackend.core.Card;
import club.libridge.libridgebackend.core.Direction;

public interface BoardDealer {

    Board dealBoard(Direction dealer, Deque<Card> deck);

}
