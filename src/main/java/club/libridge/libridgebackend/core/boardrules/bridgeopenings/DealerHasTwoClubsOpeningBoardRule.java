package club.libridge.libridgebackend.core.boardrules.bridgeopenings;

import club.libridge.libridgebackend.core.Board;
import club.libridge.libridgebackend.core.Hand;
import club.libridge.libridgebackend.core.HandEvaluations;
import club.libridge.libridgebackend.core.boardrules.BoardRule;

public class DealerHasTwoClubsOpeningBoardRule extends SingletonEqualsAndHashcode implements BoardRule {

    @Override
    public boolean isValid(Board board) {
        Hand dealerHand = board.getHandOf(board.getDealer());
        HandEvaluations handEvaluations = dealerHand.getHandEvaluations();
        return handEvaluations.getHCP() >= 22;
    }

}
