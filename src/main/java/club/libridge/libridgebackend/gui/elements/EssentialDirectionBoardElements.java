package club.libridge.libridgebackend.gui.elements;

import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.List;

import club.libridge.libridgebackend.core.Deal;
import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.gui.constants.FrameConstants;

public class EssentialDirectionBoardElements {

        public EssentialDirectionBoardElements(Deal deal, Container container, ActionListener actionListener,
                        List<String> spectators, Direction myDirection, String gameName) {
                for (Direction direction : Direction.values()) {
                        boolean isVisible = direction.equals(myDirection) || deal.isFinished()
                                        || direction.equals(deal.getClaimer());
                        new HandElement(deal, container, actionListener, FrameConstants.getPointOfDirection(direction),
                                        deal.getPlayerOf(direction), isVisible, direction);
                }

                new StrainElement(deal.getRuleset(), container, FrameConstants.getRulesetPosition());

                new LeaveTableElement(container, new Point(150, 50), actionListener);

                new SpectatorsElement(container, FrameConstants.getSpectatorNamesPosition(), spectators);

                GameNameElement.addGameNameToContainerAtPosition(gameName, container,
                                new Point(FrameConstants.getHalfWidth(), 10));
        }

}
