package club.libridge.libridgebackend.core.exceptions;

@SuppressWarnings("serial")
public class SuitDoesNotExistException extends RuntimeException {
    public SuitDoesNotExistException() {
        super("This suit does not exist. Please use the correct abbreviation.");
    }
}
