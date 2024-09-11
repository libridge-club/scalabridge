package scalabridge

import scala.collection.immutable.ListMap

case class Card(suit: Suit, rank: Rank) {
  def compareRank(otherCard: Card): Int = this.rank.compareTo(otherCard.rank)
  def compareSuit(otherCard: Card): Int = this.suit.compareTo(otherCard.suit)
  def getPoints: Int = Card.getPoints(this)
  override def toString: String = this.suit.getSymbol + this.rank.getSymbol
  def getSuit: Suit = this.suit;
  def getRank: Rank = this.rank;
}
private case object Card {
  // Guessing a ListMap is faster than a HashMap here. If this becomes an issue:
  // Benchmark it, write the results here and refactor.
  private val pointsMap = ListMap(
    Rank.ACE -> 4,
    Rank.KING -> 3,
    Rank.QUEEN -> 2,
    Rank.JACK -> 1
  )
  private def getPoints(card: Card): Int = pointsMap.getOrElse(card.rank, 0)
}
