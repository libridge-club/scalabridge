package club.libridge.libridgebackend.core.rulesets.interfaces;

import java.util.Comparator;

import club.libridge.libridgebackend.core.Card;

public interface CardComparable {

  Comparator<Card> getComparator();

}
