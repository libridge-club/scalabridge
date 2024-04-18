package club.libridge.libridgebackend.networking.rest;

public class IdentifierNotSetException extends RuntimeException {
    public IdentifierNotSetException() {
        super("The client does not know its own identifier yet.");
    }
}
