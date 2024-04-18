package club.libridge.libridgebackend.gui.painters;

import java.awt.Container;

import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.gui.elements.ChooseStrainElement;
import club.libridge.libridgebackend.gui.elements.EssentialDirectionBoardElements;
import club.libridge.libridgebackend.gui.elements.WaitingForChooserElement;
import club.libridge.libridgebackend.networking.client.SBKingClient;

public class WaitingForChoosingStrainPainter implements Painter {

    private Direction myDirection;
    private Direction chooserDirection;
    private SBKingClient sbKingClient;

    public WaitingForChoosingStrainPainter(Direction myDirection, Direction chooserDirection,
            SBKingClient sbKingClient) {
        this.myDirection = myDirection;
        this.chooserDirection = chooserDirection;
        this.sbKingClient = sbKingClient;
    }

    @Override
    public void paint(Container contentPane) {
        if (myDirection != chooserDirection) {
            WaitingForChooserElement.add(contentPane, chooserDirection, "Game Mode or Strain.");
        } else {
            ChooseStrainElement chooseGameModeOrStrainElement = new ChooseStrainElement(contentPane,
                    this.sbKingClient);
            chooseGameModeOrStrainElement.add();
        }

        new EssentialDirectionBoardElements(this.sbKingClient.getDeal(), contentPane,
                this.sbKingClient.getActionListener(), this.sbKingClient.getSpectatorNames(), myDirection,
                this.sbKingClient.getGameName());

        contentPane.validate();
        contentPane.repaint();

    }

}
