package club.libridge.libridgebackend.core.exceptions;

@SuppressWarnings("serial")
public class MalformedLinMDValueException extends RuntimeException {
    public MalformedLinMDValueException() {
        super("The LIN md value is malformed.");
    }

    public MalformedLinMDValueException(String message) {
        super("The LIN md value is malformed: " + message);
    }
}
