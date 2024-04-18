package club.libridge.libridgebackend.networking.rest;

public class CouldNotSendRequestException extends RuntimeException {
    public CouldNotSendRequestException(Exception e) {
        super(e);
    }
}
