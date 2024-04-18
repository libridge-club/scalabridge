package club.libridge.libridgebackend.gui.elements;

import java.awt.Container;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import club.libridge.libridgebackend.gui.constants.FrameConstants;
import club.libridge.libridgebackend.gui.jelements.SBKingLabel;
import club.libridge.libridgebackend.gui.models.TextWithColorAndFont;
import club.libridge.libridgebackend.networking.client.SBKingClient;

public class CreateTableElement {

  private Container container;
  private List<JRadioButton> radioButtons;
  private SBKingClient sbKingClient;
  private int numberOfElements;
  private int elementWidth;
  private int elementHeight;
  private int xSpacing;
  private int yLabelOffset;
  List<TextWithColorAndFont> texts;

  private static final int BUTTON_WIDTH = 100;

  public CreateTableElement(Container container, SBKingClient sbKingClient) {
    this.container = container;
    this.sbKingClient = sbKingClient;
    this.numberOfElements = 4;
    this.elementWidth = 200;

    this.texts = new ArrayList<TextWithColorAndFont>();
    texts.add(new TextWithColorAndFont("Minibridge"));

    int initialX = FrameConstants.getHalfWidth();
    int initialY = FrameConstants.getHalfHeight();

    elementHeight = 20;
    xSpacing = 5;
    yLabelOffset = -elementHeight;

    int totalWidth = elementWidth + xSpacing + BUTTON_WIDTH;
    int totalHeight = elementHeight * numberOfElements;
    initialX -= totalWidth / 2;
    initialY -= totalHeight / 2;

    Point buttonPosition = new Point(initialX, initialY);
    this.addRadioButtons(buttonPosition);
    Point labelPosition = new Point(initialX, initialY);
    labelPosition.translate(0, yLabelOffset);
    this.addLabel(labelPosition);
  }

  private void addRadioButtons(Point buttonsPosition) {
    int y = buttonsPosition.y;
    int x = buttonsPosition.x;

    radioButtons = new ArrayList<JRadioButton>();
    SBKingRadioButtonGroupCreator sbKingRadioButtonGroupCreator = new SBKingRadioButtonGroupCreator();
    ButtonGroup buttonGroup = sbKingRadioButtonGroupCreator.create(texts, x, y, elementWidth);
    for (Enumeration<AbstractButton> elements = buttonGroup.getElements(); elements.hasMoreElements();) {
      AbstractButton element = elements.nextElement();
      container.add(element);
      radioButtons.add((JRadioButton) element);
    }

    JButton selectButton = new JButton();

    Point selectButtonPosition = new Point(buttonsPosition);
    selectButtonPosition.translate(elementWidth + xSpacing, 0);
    selectButton.addActionListener(new CreateTableSelectActionListener());
    selectButton.setLocation(selectButtonPosition);
    selectButton.setSize(BUTTON_WIDTH, elementHeight * numberOfElements);
    selectButton.setText("Create");
    container.add(selectButton);
  }

  class CreateTableSelectActionListener implements java.awt.event.ActionListener {
    public void actionPerformed(java.awt.event.ActionEvent event) {
      JRadioButton selectedOnRadio = null;
      for (JRadioButton jRadioButton : radioButtons) {
        if (jRadioButton.isSelected()) {
          selectedOnRadio = jRadioButton;
          break;
        }
      }
      if (selectedOnRadio != null) {
        UUID tableID = sbKingClient.sendCreateTable(selectedOnRadio.getText());
        sbKingClient.sendJoinTable(tableID);
      }
    }
  }

  private void addLabel(Point labelPosition) {
    int labelWidth = 160;
    JLabel waitingLabel = new SBKingLabel("Create new table:");
    waitingLabel.setSize(labelWidth, elementHeight);
    waitingLabel.setLocation(labelPosition);
    container.add(waitingLabel);
  }

}