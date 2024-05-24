package club.libridge.libridgebackend.core.exceptions;

@SuppressWarnings("serial")
public class DirectionDoesNotExist extends RuntimeException {
    public DirectionDoesNotExist() {
        super("This direction does not exist. Please use the correct abbreviation.");
    }
}
