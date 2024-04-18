package club.libridge.libridgebackend.core.boarddealer;

import java.util.Deque;

import club.libridge.libridgebackend.core.Card;

public interface CardDeck {

  Deque<Card> getDeck();

}
