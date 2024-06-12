package club.libridge.libridgebackend.app;

import static club.libridge.libridgebackend.logging.LibridgeLogger.LOGGER;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class SecurityUtils {

    private SecurityUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static byte[] getSHA256Sum(String anyString) {
        byte[] response = new byte[1];
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(anyString.getBytes());
            response = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return response;
    }

}
