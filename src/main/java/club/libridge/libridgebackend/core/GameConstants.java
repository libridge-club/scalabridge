package club.libridge.libridgebackend.core;

public final class GameConstants {

    private GameConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final int NUMBER_OF_HANDS = 4;
    public static final int SIZE_OF_HAND = 13;
    public static final int NUMBER_OF_TRICKS_IN_A_COMPLETE_HAND = SIZE_OF_HAND;
    public static final int COMPLETE_TRICK_NUMBER_OF_CARDS = NUMBER_OF_HANDS;

}
