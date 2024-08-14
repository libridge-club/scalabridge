package scalabridge

case class Card(suit: Suit, rank: Rank) {
  def compareRank(otherCard: Card): Int = this.rank.compareTo(otherCard.rank)
  def compareSuit(otherCard: Card): Int = this.suit.compareTo(otherCard.suit)
  override def toString: String = this.suit.getSymbol + this.rank.getSymbol
  def getPoints: Int = Card.getPoints(this)
  def getSuit: Suit = this.suit
  def getRank: Rank = this.rank
}
private case object Card {
  private val points: Map[Rank, Int] = Map[Rank, Int](
    Rank.ACE -> 4,
    Rank.KING -> 3,
    Rank.QUEEN -> 2,
    Rank.JACK -> 1
  )
  def getPoints(card: Card): Int = points.getOrElse(card.rank, 0)
}
