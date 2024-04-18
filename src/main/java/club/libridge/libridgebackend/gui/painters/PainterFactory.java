package club.libridge.libridgebackend.gui.painters;

import static club.libridge.libridgebackend.logging.SBKingLogger.LOGGER;

import java.awt.event.ActionListener;

import club.libridge.libridgebackend.core.Deal;
import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.networking.client.SBKingClient;

public class PainterFactory {

  private SBKingClient sbkingClient;

  public PainterFactory(SBKingClient sbkingClient) {
    this.sbkingClient = sbkingClient;
  }

  public Painter getDealPainter(Deal deal, Direction direction, ActionListener actionListener) {
    return new DealPainter(actionListener, direction, deal, this.sbkingClient.getSpectatorNames(),
        this.sbkingClient.getGameName());
  }

  public Painter getSpectatorPainter(Deal deal, ActionListener actionListener) {
    if (deal == null) {
      LOGGER.error("Deal should not be null here.");
      return null;
    } else {
      return new SpectatorPainter(actionListener, deal, this.sbkingClient.getSpectatorNames(),
          this.sbkingClient.getGameName());
    }
  }

  public Painter getWaitingForChoosingStrainPainter(Direction direction, Direction chooser) {
    return new WaitingForChoosingStrainPainter(direction, chooser, this.sbkingClient);
  }

  public Painter getDealWithDummyPainter(Deal deal, Direction direction, ActionListener actionListener) {
    boolean dummyVisible = this.sbkingClient.getDeal().isDummyOpen();
    Direction dummy = this.sbkingClient.getDeal().getDummy();
    return new DealWithDummyPainter(actionListener, direction, deal, dummy, dummyVisible,
        this.sbkingClient.getSpectatorNames(), this.sbkingClient.getGameName());
  }

}
