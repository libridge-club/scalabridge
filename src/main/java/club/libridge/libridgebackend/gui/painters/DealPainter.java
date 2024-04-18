package club.libridge.libridgebackend.gui.painters;

import static club.libridge.libridgebackend.logging.SBKingLogger.LOGGER;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.util.List;

import club.libridge.libridgebackend.core.Deal;
import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.gui.elements.SpecificDirectionBoardElements;

public class DealPainter implements Painter {

    protected Direction direction;
    protected ActionListener actionListener;
    protected Deal deal;
    protected List<String> spectators;
    protected String gameName;

    public DealPainter(ActionListener actionListener, Direction direction, Deal deal, List<String> spectators,
            String gameName) {
        this.actionListener = actionListener;
        this.direction = direction;
        this.deal = deal;
        this.spectators = spectators;
        this.gameName = gameName;
    }

    @Override
    public void paint(Container contentPane) {
        LOGGER.trace("Painting deal that contains this trick: {}", deal.getCurrentTrick());
        contentPane.removeAll();

        // FIXME This should be uncommented when there is a good way to show the last
        // card for a second before showing the scoresummary
        // if (deal.isFinished()) {
        // new ScoreSummaryElement(deal, contentPane);
        // } else {
        new SpecificDirectionBoardElements(this.direction, deal, contentPane, actionListener, this.spectators,
                this.gameName);
        // }

        contentPane.validate();
        contentPane.repaint();
    }

}
