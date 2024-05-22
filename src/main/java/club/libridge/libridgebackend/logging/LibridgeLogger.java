package club.libridge.libridgebackend.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LibridgeLogger {

    private LibridgeLogger() {
        throw new IllegalStateException("Utility class");
    }

    public static final Logger LOGGER = LogManager.getLogger(LibridgeLogger.class);

}
