package club.libridge.libridgebackend.gui.main;

import static club.libridge.libridgebackend.logging.SBKingLogger.LOGGER;

import club.libridge.libridgebackend.core.constants.ErrorCodes;
import club.libridge.libridgebackend.gui.frames.SBKingClientJFrame;
import club.libridge.libridgebackend.gui.screens.GameScreen;
import club.libridge.libridgebackend.gui.screens.LobbyScreen;
import club.libridge.libridgebackend.gui.screens.SBKingScreen;
import club.libridge.libridgebackend.gui.screens.WelcomeScreen;
import club.libridge.libridgebackend.networking.client.SBKingClient;
import club.libridge.libridgebackend.networking.messages.GameScreenFromGameNameIdentifier;

public class MainNetworkGame {

    SBKingClient sbkingClient;
    SBKingClientJFrame sbkingClientJFrame;

    public MainNetworkGame(SBKingClient sbkingClient, SBKingClientJFrame sbkingClientJFrame) {
        this.sbkingClient = sbkingClient;
        this.sbkingClientJFrame = sbkingClientJFrame;
    }

    public void run() {
        WelcomeScreen welcomeScreen = new WelcomeScreen(sbkingClient);
        welcomeScreen.runAt(sbkingClientJFrame);

        SBKingScreen currentScreen;

        SpectatorNamesUpdater tableUpdater = new SpectatorNamesUpdater(sbkingClient);
        Thread tableUpdaterThread = new Thread(tableUpdater, "spectator-names-updater");
        tableUpdaterThread.start();

        while (true) {

            String gameName = sbkingClient.getGameName();
            if (gameName == null) {
                sbkingClient.initializeTables();
            }

            currentScreen = new LobbyScreen(sbkingClient);
            currentScreen.runAt(sbkingClientJFrame);

            LOGGER.info("Finished Lobby Screen, creating table screen");
            sbkingClient.resetBeforeEnteringTable();

            Class<? extends GameScreen> gameScreenClass = GameScreenFromGameNameIdentifier
                    .identify(sbkingClient.getGameName());
            GameScreen gameScreen;
            try {
                Class[] constructorArguments = new Class[1];
                constructorArguments[0] = SBKingClient.class;
                gameScreen = gameScreenClass.getDeclaredConstructor(constructorArguments).newInstance(sbkingClient);
                LOGGER.info("Created GameScreen: {}", gameScreen.getClass());
                currentScreen = gameScreen;
                currentScreen.runAt(sbkingClientJFrame);
            } catch (Exception e) {
                LOGGER.fatal("Could not initialize GameScreen with received gameScreenClass.");
                LOGGER.fatal(e);
                System.exit(ErrorCodes.COULD_NOT_INITIALIZE_GAMESCREEN);
            }
        }
    }

}
