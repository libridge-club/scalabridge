package club.libridge.libridgebackend.gui.screens;

import static club.libridge.libridgebackend.logging.SBKingLogger.LOGGER;

import club.libridge.libridgebackend.core.Deal;
import club.libridge.libridgebackend.gui.frames.SBKingClientJFrame;
import club.libridge.libridgebackend.gui.main.ClientApplicationState;
import club.libridge.libridgebackend.gui.painters.Painter;
import club.libridge.libridgebackend.networking.client.SBKingClient;

public class MinibridgeScreen extends GameScreen {

  public MinibridgeScreen(SBKingClient sbkingClient) {
    super(sbkingClient);
  }

  @Override
  public void runAt(SBKingClientJFrame sbkingClientJFrame) {
    waitForDirection();

    while (true) {
      sleepFor(WAIT_FOR_REDRAW_IN_MILISECONDS);
      if (!this.checkIfStillIsOnGameScreen()) {
        return; // Exits when server says it is not on the game anymore.
      }
      if (sbkingClient.isSpectator()) {
        if (sbkingClient.getDealHasChanged() || ClientApplicationState.getGUIHasChanged()) {
          if (!ClientApplicationState.getGUIHasChanged()) {
            LOGGER.trace("Deal has changed. Painting deal.");
            LOGGER.trace("It is a spectator.");
          }
          Deal currentDeal = sbkingClient.getDeal();
          if (currentDeal != null) {
            Painter painter = this.painterFactory.getSpectatorPainter(currentDeal, sbkingClient.getActionListener());
            sbkingClientJFrame.paintPainter(painter);
          }
        }
      } else {
        if (sbkingClient.getDealHasChanged() || ClientApplicationState.getGUIHasChanged()) {
          if (!ClientApplicationState.getGUIHasChanged()) {
            LOGGER.trace("Deal has changed. Painting deal.");
            LOGGER.trace("It is a player.");
          }
          LOGGER.trace("Starting to paint Deal");
          Painter painter = this.painterFactory.getDealWithDummyPainter(sbkingClient.getDeal(),
              sbkingClient.getDirection(), sbkingClient.getActionListener());
          sbkingClientJFrame.paintPainter(painter);
          LOGGER.trace("Finished painting Deal");
        } else {
          if (!sbkingClient.isRulesetValidSet()) {
            LOGGER.debug("Suit not selected yet!");
            if (sbkingClient.getDirection() == null || !sbkingClient.isStrainChooserSet()) {
              LOGGER.trace("Direction not set yet.");
              LOGGER.trace("or Chooser not set yet.");
              continue;
            } else {
              LOGGER.trace("paintWaitingForChoosingStrainScreen!");
              LOGGER.trace("My direction: {}", sbkingClient.getDirection());
              LOGGER.trace("Chooser: {}", sbkingClient.getStrainChooser());

              Painter painter = this.painterFactory.getWaitingForChoosingStrainPainter(
                  sbkingClient.getDirection(), sbkingClient.getStrainChooser());
              sbkingClientJFrame.paintPainter(painter);
              while (!sbkingClient.isRulesetValidSet()) {
                sleepFor(WAIT_FOR_SERVER_MESSAGE_IN_MILISECONDS);
              }
            }
          }
        }
      }
    }
  }

}
