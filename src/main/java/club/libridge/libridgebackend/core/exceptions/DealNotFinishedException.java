package club.libridge.libridgebackend.core.exceptions;

@SuppressWarnings("serial")
public class DealNotFinishedException extends RuntimeException {

    public DealNotFinishedException() {
        super("The deal is not finished yet.");
    }

}
