package club.libridge.libridgebackend.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Suit {
    DIAMONDS("Diamonds", "d", '\u2666'), CLUBS("Clubs", "c", '\u2663'), HEARTS("Hearts", "h", '\u2665'), SPADES("Spades", "s", '\u2660');

    private final String name;
    private final String symbol;
    private final char unicodeSymbol;

}
