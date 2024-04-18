package club.libridge.libridgebackend.gui.elements;

import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.List;

import club.libridge.libridgebackend.dto.LobbyScreenTableDTO;
import club.libridge.libridgebackend.gui.constants.FrameConstants;

public class AllTableCardsElement {

  public AllTableCardsElement(Container container, List<LobbyScreenTableDTO> tables, ActionListener actionListener) {

    Point position = new Point(FrameConstants.getTableWidth() / 4, FrameConstants.getTableHeight() / 5);
    for (LobbyScreenTableDTO table : tables) {
      new TableCardElement(container, table, (Point) position.clone(), actionListener);
      position.translate(200, 0);
    }
  }

}
