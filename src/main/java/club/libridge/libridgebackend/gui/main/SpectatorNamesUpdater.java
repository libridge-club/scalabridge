package club.libridge.libridgebackend.gui.main;

import static club.libridge.libridgebackend.logging.SBKingLogger.LOGGER;

import club.libridge.libridgebackend.networking.client.SBKingClient;

public class SpectatorNamesUpdater implements Runnable {

  private static final int UPDATE_SPECTATOR_NAMES_TIMEOUT = 3000;
  private boolean shouldStop = false;
  private SBKingClient sbkingClient;

  public SpectatorNamesUpdater(SBKingClient sbkingClient) {
    this.sbkingClient = sbkingClient;
  }

  @Override
  public void run() {
    while (!this.shouldStop) {
      this.sbkingClient.sendGetSpectatorNames();
      try {
        Thread.sleep(UPDATE_SPECTATOR_NAMES_TIMEOUT);
      } catch (InterruptedException e) {
        LOGGER.error(e);
      }
    }
  }

  public void shouldStop() {
    this.shouldStop = true;
    this.sbkingClient = null;
  }
}
