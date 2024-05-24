package club.libridge.libridgebackend.core.exceptions;

@SuppressWarnings("serial")
public class ImpossibleBoardException extends RuntimeException {
    public ImpossibleBoardException() {
        super("It is not possible to deal such a board");
    }
}
