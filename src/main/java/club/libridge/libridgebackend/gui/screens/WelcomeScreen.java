package club.libridge.libridgebackend.gui.screens;

import static club.libridge.libridgebackend.logging.SBKingLogger.LOGGER;

import club.libridge.libridgebackend.gui.frames.SBKingClientJFrame;
import club.libridge.libridgebackend.gui.painters.SetNicknameScreenPainter;
import club.libridge.libridgebackend.networking.client.SBKingClient;

public class WelcomeScreen implements SBKingScreen {

  private boolean connectedToServer = false;
  private SBKingClient sbkingClient;

  private static final int WAIT_FOR_SERVER_MESSAGE_IN_MILISECONDS = 10;

  public WelcomeScreen(SBKingClient sbkingClient) {
    super();
    this.sbkingClient = sbkingClient;
  }

  public void setAndSendNickname(String nickname) {
    this.sbkingClient.setNickname(nickname);
    this.sbkingClient.sendNickname();
  }

  public boolean isConnectedToServer() {
    return connectedToServer;
  }

  public SBKingClient getSBKingClient() {
    return sbkingClient;
  }

  @Override
  public void runAt(SBKingClientJFrame sbkingClientJFrame) {
    LOGGER.debug("Starting to paint WelcomeScreen");
    sbkingClientJFrame.paintPainter(new SetNicknameScreenPainter(this));
    LOGGER.debug("Finished painting WelcomeScreen");

    LOGGER.info("Waiting for nickname to be set");
    while (!this.sbkingClient.isNicknameSet()) {
      sleepFor(WAIT_FOR_SERVER_MESSAGE_IN_MILISECONDS);
    }
    LOGGER.info("nicknameSet is true. Leaving WelcomeScreen");
  }

  private void sleepFor(int miliseconds) {
    try {
      Thread.sleep(miliseconds);
    } catch (InterruptedException e) {
      LOGGER.error(e);
    }
  }

}
