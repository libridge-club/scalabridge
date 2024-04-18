package club.libridge.libridgebackend.core;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

import club.libridge.libridgebackend.core.exceptions.DealingCardFromAnEmptyDeckException;

public class ShuffledDeck {
    private List<Card> deck; // List is best because we need to shuffle it
    private Iterator<Card> iterator;

    public ShuffledDeck(Deque<Card> deck) {
        this.deck = new ArrayList<Card>(deck);
        new RandomUtils().shuffle(this.deck);
        this.iterator = this.deck.iterator();
    }

    public Card dealCard() {
        if (this.iterator.hasNext()) {
            return this.iterator.next();
        }
        throw new DealingCardFromAnEmptyDeckException();
    }

    public boolean hasCard() {
        return this.iterator.hasNext();
    }

}
