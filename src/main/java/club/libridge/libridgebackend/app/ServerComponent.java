package club.libridge.libridgebackend.app;

import static club.libridge.libridgebackend.logging.SBKingLogger.LOGGER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import club.libridge.libridgebackend.networking.server.SBKingServer;

@Component
public class ServerComponent {

    private SBKingServer sbKingServer;

    public SBKingServer getSbKingServer() {
        return sbKingServer;
    }

    public ServerComponent(@Autowired PlayerController playerController, @Autowired TableController tableController) {
        this.sbKingServer = new SBKingServer(playerController, tableController);
        LOGGER.info("SBKingServer created.");
    }

}
