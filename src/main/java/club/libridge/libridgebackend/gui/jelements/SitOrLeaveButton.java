package club.libridge.libridgebackend.gui.jelements;

import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.gui.constants.FrameConstants;

@SuppressWarnings("serial")
public class SitOrLeaveButton extends SBKingButton {

  private static final int DEFAULT_WIDTH = 400;
  private static final int DEFAULT_HEIGHT = 30;

  public SitOrLeaveButton(Direction direction) {
    super();
    this.putClientProperty("type", "SITORLEAVE");
    this.putClientProperty("direction", direction);
    double scaleFactor = FrameConstants.getScreenScale();
    int width = (int) (DEFAULT_WIDTH * scaleFactor);
    int height = (int) (DEFAULT_HEIGHT * scaleFactor);
    this.setSize(width, height);
  }

}
