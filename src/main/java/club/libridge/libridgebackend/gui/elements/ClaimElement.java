
package club.libridge.libridgebackend.gui.elements;

import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.gui.jelements.ClaimButton;

public class ClaimElement {

    public ClaimElement(Direction claimer, Container container, Point point, ActionListener actionListener) {
        JButton claimButton = new ClaimButton();
        claimButton.addActionListener(actionListener);
        if (claimer == null) {
            claimButton.setText("CLAIM");
            claimButton.setBounds(point.x, point.y, 100, 30);
            container.add(claimButton);
        }
    }
}
