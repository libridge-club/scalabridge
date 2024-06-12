package club.libridge.libridgebackend.app;

import static club.libridge.libridgebackend.logging.LibridgeLogger.LOGGER;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class SecurityUtils {

    private SecurityUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getSHA256Sum(String anyString) {
        String response = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(anyString.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            response = new String(digest, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return response;
    }

}
