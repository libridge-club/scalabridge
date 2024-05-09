package club.libridge.libridgebackend.pbn;

import java.util.Collection;
import java.util.List;

import club.libridge.libridgebackend.core.Board;
import club.libridge.libridgebackend.core.Card;
import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.core.Hand;
import club.libridge.libridgebackend.core.Suit;
import club.libridge.libridgebackend.core.comparators.CardInsideHandComparator;

public final class PBNUtils {

    /**
     * 69 in fact: 52 cards + 3*4 = 12 dots, 3 spaces and a "X:" in the start
     */
    private static final int MAX_CHARS_IN_DEAL_TAG = 70;

    private PBNUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     *
     * Implemented from PBN Standard 2.1
     * - Defined at section 3.4.11  The Deal tag
     */
    public static String dealTagStringFromBoard(Board board) {
        return dealTagStringFromBoardAndDirection(board, Direction.NORTH);
    }

    /**
     *
     * Implemented from PBN Standard 2.1
     * - Defined at section 3.4.11  The Deal tag
     */
    public static String dealTagStringFromBoardAndDirection(Board board, Direction firstDirection) {
        StringBuilder returnValue = new StringBuilder(MAX_CHARS_IN_DEAL_TAG);

        for (int i = 0; i < 4; i++) {
            Direction current = firstDirection.next(i);
            Hand currentHand = board.getHandOf(current);
            if (i == 0) {
                returnValue.append(firstDirection.getAbbreviation() + ":");
            } else {
                returnValue.append(" ");
            }
            returnValue.append(dealTagPartialStringFromHand(currentHand));
        }
        return returnValue.toString();
    }

    /**
     *
     * Implemented from PBN Standard 2.1
     * - Defined at section 3.4.11  The Deal tag
     */
    private static String dealTagPartialStringFromHand(Hand hand) {
        StringBuilder returnValue = new StringBuilder(20);
        hand.sort(new CardInsideHandComparator());
        Collection<Card> cards = hand.getCards();
        List<Suit> suitsInDescendingOrder = List.of(Suit.SPADES, Suit.HEARTS, Suit.DIAMONDS, Suit.CLUBS);
        for (Suit currentSuit : suitsInDescendingOrder) {
            if (Suit.SPADES != currentSuit) {
                returnValue.append(".");
            }
            cards.stream().filter(x -> x.getSuit() == currentSuit).map(x -> x.getRank().getSymbol()).forEach(returnValue::append);
        }

        return returnValue.toString();
    }

}
