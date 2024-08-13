package club.libridge.libridgebackend.core.exceptions;

@SuppressWarnings("serial")
public class RankDoesNotExistException extends RuntimeException {
    public RankDoesNotExistException() {
        super("This rank does not exist. Please use the correct abbreviation.");
    }
}
