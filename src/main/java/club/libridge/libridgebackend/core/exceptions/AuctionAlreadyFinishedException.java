package club.libridge.libridgebackend.core.exceptions;

@SuppressWarnings("serial")
public class AuctionAlreadyFinishedException extends RuntimeException {
    public AuctionAlreadyFinishedException() {
        super("You cannot bid after the auction is finished.");
    }
}
