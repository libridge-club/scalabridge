package club.libridge.libridgebackend.clientapp;

import static club.libridge.libridgebackend.logging.SBKingLogger.LOGGER;

import org.springframework.stereotype.Component;

import club.libridge.libridgebackend.gui.frames.SBKingClientJFrame;
import club.libridge.libridgebackend.gui.main.ConnectToServer;
import club.libridge.libridgebackend.gui.main.MainNetworkGame;
import club.libridge.libridgebackend.networking.client.SBKingClient;

@Component
public class ClientComponent {

    private MainNetworkGame game;
    private SBKingClient sbkingClient;

    public ClientComponent() {
        LOGGER.trace("Initializing ClientComponent");
        this.sbkingClient = ConnectToServer.connectToServer();
        game = new MainNetworkGame(sbkingClient, new SBKingClientJFrame());
    }

    public void run() {
        game.run();
    }

    public SBKingClient getSBKingClient() {
        return this.sbkingClient;
    }

}
