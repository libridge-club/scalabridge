package club.libridge.libridgebackend.gui.painters;

import static club.libridge.libridgebackend.logging.SBKingLogger.LOGGER;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.util.List;

import club.libridge.libridgebackend.core.Deal;
import club.libridge.libridgebackend.gui.elements.AllDirectionBoardElements;

public class SpectatorPainter implements Painter {

    private ActionListener actionListener;
    private Deal deal;
    private List<String> spectators;
    private String gameName;

    public SpectatorPainter(ActionListener actionListener, Deal deal, List<String> spectators, String gameName) {
        this.actionListener = actionListener;
        this.deal = deal;
        this.spectators = spectators;
        this.gameName = gameName;
    }

    @Override
    public void paint(Container contentPane) {
        LOGGER.trace("Painting spectator that contains this trick: {}", deal.getCurrentTrick());
        contentPane.removeAll();

        if (!deal.isFinished()) {
            new AllDirectionBoardElements(deal, contentPane, this.actionListener, this.spectators, this.gameName);
        }

        contentPane.validate();
        contentPane.repaint();
    }

}
