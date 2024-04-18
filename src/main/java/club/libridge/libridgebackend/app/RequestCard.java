package club.libridge.libridgebackend.app;

import club.libridge.libridgebackend.core.Card;
import club.libridge.libridgebackend.core.Rank;
import club.libridge.libridgebackend.core.Suit;

public class RequestCard {
    private String rank;
    private String suit;

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public Card getCard() {
        Rank finalRank = null;
        Suit finalSuit = null;
        for (Rank currentRank : Rank.values()) {
            if (currentRank.getSymbol().equals(this.rank)) {
                finalRank = currentRank;
                break;
            }
        }
        for (Suit currentSuit : Suit.values()) {
            if (currentSuit.getSymbol().equals(this.suit)) {
                finalSuit = currentSuit;
                break;
            }
        }
        if (finalRank == null || finalSuit == null) {
            throw new RuntimeException();
        }
        return new Card(finalSuit, finalRank);
    }

}
