package club.libridge.libridgebackend.core.exceptions;

@SuppressWarnings("serial")
public class DirectionDoesNotExistException extends RuntimeException {
    public DirectionDoesNotExistException() {
        super("This direction does not exist. Please use the correct abbreviation.");
    }
}
