package club.libridge.libridgebackend.lin;

import lombok.Getter;
import lombok.NonNull;

public final class LinKeyValuePair {

    /**
     * Used for sanity check.
     */
    private static final int MAX_VALUE_SIZE = 1024;
    private static final String ERROR_MESSAGE = "Value length cannot be greater than " + MAX_VALUE_SIZE;

    @Getter
    private final LinKey key;
    @Getter
    private final String value;

    public LinKeyValuePair(@NonNull String key, @NonNull String value) {
        this.key = LinKey.get(key);
        if (value.length() > MAX_VALUE_SIZE) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        this.value = value;
    }

}
