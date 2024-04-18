package club.libridge.libridgebackend.gui.painters;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.util.List;

import club.libridge.libridgebackend.dto.LobbyScreenTableDTO;
import club.libridge.libridgebackend.gui.elements.AllTableCardsElement;
import club.libridge.libridgebackend.gui.elements.CreateTableElement;
import club.libridge.libridgebackend.networking.client.SBKingClient;

public class LobbyScreenPainter implements Painter {

  private List<LobbyScreenTableDTO> tables;
  private ActionListener actionListener;
  private SBKingClient sbkingClient;

  public LobbyScreenPainter(List<LobbyScreenTableDTO> tables, ActionListener actionListener,
      SBKingClient sbkingClient) {
    this.tables = tables;
    this.actionListener = actionListener;
    this.sbkingClient = sbkingClient;
  }

  @Override
  public void paint(Container contentPane) {
    new AllTableCardsElement(contentPane, this.tables, this.actionListener);
    new CreateTableElement(contentPane, this.sbkingClient);

    contentPane.validate();
    contentPane.repaint();
  }

}
