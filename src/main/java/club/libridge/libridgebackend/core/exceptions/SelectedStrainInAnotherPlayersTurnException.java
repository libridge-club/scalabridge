package club.libridge.libridgebackend.core.exceptions;

@SuppressWarnings("serial")
public class SelectedStrainInAnotherPlayersTurnException extends RuntimeException {
    public SelectedStrainInAnotherPlayersTurnException() {
        super("You cannot choose strain in another player's turn.");
    }
}
