package club.libridge.libridgebackend.gui.jelements;

import java.awt.Font;

import javax.swing.JLabel;

import club.libridge.libridgebackend.gui.constants.FontConstants;

@SuppressWarnings("serial")
public class SBKingLabel extends JLabel {

  public SBKingLabel(String text) {
    super(text);
    this.setFont(new Font(FontConstants.FONT_NAME, Font.PLAIN, 14));
    this.setForeground(new java.awt.Color(255, 255, 255));
  }
}
