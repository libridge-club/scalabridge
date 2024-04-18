package br.com.sbk.sbking.core.game;

import java.util.Deque;

import br.com.sbk.sbking.core.Card;
import br.com.sbk.sbking.core.Deal;
import br.com.sbk.sbking.core.Direction;
import br.com.sbk.sbking.core.boarddealer.BoardDealer;
import br.com.sbk.sbking.core.boarddealer.MinibridgeBoardDealer;
import br.com.sbk.sbking.core.rulesets.concrete.PositiveRuleset;

public class MinibridgeGame extends TrickGame {

  private BoardDealer boardDealer;

  public MinibridgeGame(Deque<Card> gameDeck) {
    super(gameDeck);
    this.boardDealer = new MinibridgeBoardDealer();
  }

  @Override
  public void dealNewBoard() {
    this.currentBoard = boardDealer.dealBoard(this.dealer, this.gameDeck);
    this.currentDeal = new Deal(currentBoard, new PositiveRuleset(), this.getLeader(), true);
    this.currentDeal.setCurrentPlayer(this.dealer.next());
    this.currentDeal.setDummy(this.dealer.next(2));
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void finishDeal() {
    this.dealer = this.dealer.next();
  }

  public Direction getDeclarer() {
    return this.dealer;
  }

  public Direction getDummy() {
    return this.currentDeal.getDummy();
  }

  @Override
  public Direction getLeader() {
    return dealer.next();
  }

}
