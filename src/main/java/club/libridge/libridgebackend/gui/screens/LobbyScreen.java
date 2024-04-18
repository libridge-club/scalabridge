package club.libridge.libridgebackend.gui.screens;

import static club.libridge.libridgebackend.logging.SBKingLogger.LOGGER;

import java.util.List;

import club.libridge.libridgebackend.dto.LobbyScreenTableDTO;
import club.libridge.libridgebackend.gui.frames.SBKingClientJFrame;
import club.libridge.libridgebackend.gui.main.TableUpdater;
import club.libridge.libridgebackend.gui.painters.LobbyScreenPainter;
import club.libridge.libridgebackend.networking.client.SBKingClient;

public class LobbyScreen implements SBKingScreen {

  private SBKingClient sbkingClient;

  private static final int DELAY_FOR_UPDATING_TABLES = 1000;

  public LobbyScreen(SBKingClient sbkingClient) {
    this.sbkingClient = sbkingClient;
  }

  @Override
  public void runAt(SBKingClientJFrame sbkingClientJFrame) {
    LOGGER.info("Entered Lobby Screen");
    this.paintEverything(sbkingClientJFrame);

    TableUpdater tableUpdater = new TableUpdater(sbkingClient);
    Thread tableUpdaterThread = new Thread(tableUpdater, "table-updater");
    tableUpdaterThread.start();

    LOGGER.info("Waiting to receive gameName from server.");
    while (this.sbkingClient.getGameName() == null) {
      if (this.sbkingClient.shouldRedrawTables()) {
        this.paintEverything(sbkingClientJFrame);
      }
      sleepFor(DELAY_FOR_UPDATING_TABLES);
    }
    tableUpdater.shouldStop();
  }

  public List<LobbyScreenTableDTO> getTables() {
    return this.sbkingClient.getTables();
  }

  private void paintEverything(SBKingClientJFrame sbkingClientJFrame) {
    sbkingClientJFrame
        .paintPainter(
            new LobbyScreenPainter(this.getTables(), this.sbkingClient.getActionListener(), this.sbkingClient));
  }

  private void sleepFor(int miliseconds) {
    try {
      Thread.sleep(miliseconds);
    } catch (InterruptedException e) {
      LOGGER.error(e);
    }
  }
}
