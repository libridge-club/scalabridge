package club.libridge.libridgebackend.core.exceptions;

@SuppressWarnings("serial")
public class DoesNotFollowSuitException extends RuntimeException {
    public DoesNotFollowSuitException() {
        super("You have to follow suit.");
    }
}
