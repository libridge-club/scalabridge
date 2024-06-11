package club.libridge.libridgebackend.core.boardrules.bridgeopenings;

import club.libridge.libridgebackend.core.Board;
import club.libridge.libridgebackend.core.boardrules.BoardRule;

public class AlwaysValidBoardRule extends SingletonEqualsAndHashcode implements BoardRule {

    @Override
    public boolean isValid(Board board) {
        return true;
    }

}
