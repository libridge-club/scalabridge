package club.libridge.libridgebackend.core.boarddealer;

import java.util.Deque;
import java.util.EnumMap;
import java.util.Map;

import club.libridge.libridgebackend.core.Board;
import club.libridge.libridgebackend.core.Card;
import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.core.Hand;
import club.libridge.libridgebackend.core.ShuffledDeck;

public class ShuffledBoardDealer implements BoardDealer {

  private Map<Direction, Hand> hands;

  @Override
  public Board dealBoard(Direction dealer, Deque<Card> deck) {
    Direction currentDirection;
    Hand currentHand;
    ShuffledDeck currentDeck = new ShuffledDeck(deck);
    hands = new EnumMap<Direction, Hand>(Direction.class);
    for (Direction direction : Direction.values()) {
      hands.put(direction, new Hand());
    }
    for (currentDirection = dealer; currentDeck.hasCard(); currentDirection = currentDirection.next()) {
      currentHand = this.hands.get(currentDirection);
      currentHand.addCard(currentDeck.dealCard());
    }
    return new Board(hands, dealer);
  }

}
