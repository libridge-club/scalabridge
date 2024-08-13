package club.libridge.libridgebackend.core;

import club.libridge.libridgebackend.core.exceptions.SuitDoesNotExistException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Suit {
    DIAMONDS("Diamonds", "d", '\u2666'), CLUBS("Clubs", "c", '\u2663'), HEARTS("Hearts", "h", '\u2665'), SPADES("Spades", "s", '\u2660');

    private final String name;
    private final String symbol;
    private final char unicodeSymbol;

    // Static copy to avoid many copies
    private static Suit[] vals = values();

    public static Suit getFromAbbreviation(char abbreviation) {
        char lowercase = Character.toLowerCase(abbreviation);
        for (Suit suit : vals) {
            if (suit.symbol.toLowerCase().charAt(0) == lowercase) {
                return suit;
            }
        }
        throw new SuitDoesNotExistException();
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getName() {
        return this.name;
    }

    public char getUnicodeSymbol() {
        return this.unicodeSymbol;
    }
}
