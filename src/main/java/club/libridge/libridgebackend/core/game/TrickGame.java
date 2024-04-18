package club.libridge.libridgebackend.core.game;

import java.util.Deque;

import club.libridge.libridgebackend.core.Board;
import club.libridge.libridgebackend.core.Card;
import club.libridge.libridgebackend.core.Deal;
import club.libridge.libridgebackend.core.Direction;
import club.libridge.libridgebackend.core.Player;
import club.libridge.libridgebackend.core.rulesets.abstractrulesets.Ruleset;

public abstract class TrickGame {

    protected Board currentBoard;
    protected Deal currentDeal;
    protected Direction dealer = Direction.NORTH;
    protected Deque<Card> gameDeck;

    protected TrickGame(Deque<Card> gameDeck) {
        super();
        this.gameDeck = gameDeck;
    }

    public abstract void dealNewBoard();

    public Direction getDealer() {
        return this.dealer;
    }

    public Board getCurrentBoard() {
        return this.currentBoard;
    }

    public Deal getCurrentDeal() {
        return this.currentDeal;
    }

    public abstract boolean isFinished();

    public abstract void finishDeal();

    public abstract Direction getLeader();

    public void setPlayerOf(Direction direction, Player player) {
        this.currentDeal.setPlayerOf(direction, player);
    }

    public void setRuleset(Ruleset ruleset) {
        this.currentDeal.setRuleset(ruleset);
    }

}
