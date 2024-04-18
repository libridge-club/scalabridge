package club.libridge.libridgebackend.core.exceptions;

@SuppressWarnings("serial")
public class CallInAnotherPlayersTurnException extends RuntimeException {
    public CallInAnotherPlayersTurnException() {
        super("You cannot call in another player's turn.");
    }
}


