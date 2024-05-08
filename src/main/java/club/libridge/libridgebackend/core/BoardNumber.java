package club.libridge.libridgebackend.core;

/**
 *
 * LAW 2 does not specify a MAX_BOARD_NUMBER. We arbitrarily chose 256 but this number is usually 32.
 */
public final class BoardNumber {

    private static final int MIN_BOARD_NUMBER = 1;
    private static final int MAX_BOARD_NUMBER = 256;

    private int number;

    public BoardNumber(int number) {
        if (isValid(number)) {
            this.number = number;
        } else {
            throw new IllegalArgumentException("Board number should be between " + MIN_BOARD_NUMBER + " and " + MAX_BOARD_NUMBER + " inclusive.");
        }
    }

    private boolean isValid(int number) {
        return number >= MIN_BOARD_NUMBER && number <= MAX_BOARD_NUMBER;
    }

    public int getNumber() {
        return this.number;
    }

}
