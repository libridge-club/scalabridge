package club.libridge.libridgebackend.core.boardrules;

import club.libridge.libridgebackend.core.Board;

public interface BoardRule {

  boolean isValid(Board board);

}
