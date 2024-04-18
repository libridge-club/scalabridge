package club.libridge.libridgebackend.gui.painters;

import java.awt.Container;

import club.libridge.libridgebackend.gui.elements.WaitingForPlayersLabelElement;

public class WaitingForPlayersPainter implements Painter {

    @Override
    public void paint(Container contentPane) {
        WaitingForPlayersLabelElement.add(contentPane);
        contentPane.validate();
        contentPane.repaint();
    }

}
