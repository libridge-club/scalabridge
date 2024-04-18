package club.libridge.libridgebackend.core.comparators;

import java.util.Comparator;

import club.libridge.libridgebackend.core.Card;

public class RankComparator implements Comparator<Card> {

  @Override
  public int compare(Card card1, Card card2) {
    return card1.compareRank(card2);
  }

}
