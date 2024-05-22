package club.libridge.libridgebackend.core;

public final class NumberOfTricks {

    private static final int MIN_NUMBER_OF_TRICKS = 0;
    private static final int MAX_NUMBER_OF_TRICKS = 13;

    private final int number;

    public NumberOfTricks(int number) {
        if (isValid(number)) {
            this.number = number;
        } else {
            throw new IllegalArgumentException(
                    "Number of tricks should be between " + MIN_NUMBER_OF_TRICKS + " and " + MAX_NUMBER_OF_TRICKS + " inclusive.");
        }
    }

    private boolean isValid(int number) {
        return number >= MIN_NUMBER_OF_TRICKS && number <= MAX_NUMBER_OF_TRICKS;
    }

    public int getInt() {
        return this.number;
    }

}
