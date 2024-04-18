package club.libridge.libridgebackend.core.boarddealer;

import java.util.ArrayDeque;
import java.util.Deque;

import club.libridge.libridgebackend.core.Card;
import club.libridge.libridgebackend.core.Rank;
import club.libridge.libridgebackend.core.Suit;

public class Partial40CardDeck implements CardDeck {

  private Deque<Card> deck;

  public Partial40CardDeck() {
    this.deck = new ArrayDeque<Card>();
    for (Suit suit : Suit.values()) {
      for (Rank rank : Rank.values()) {
        if (rank.compareTo(Rank.FIVE) >= 0) { // Rank is greater or equal than five
          Card card = new Card(suit, rank);
          this.deck.add(card);
        }
      }
    }
  }

  public Deque<Card> getDeck() {
    return this.deck;
  }

}
