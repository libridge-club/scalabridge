package scalabridge.pbn

import scalabridge.Card
import scalabridge.Direction
import scalabridge.Hand
import scalabridge.Rank
import scalabridge.Suit

import scala.util.matching.Regex

object PBNUtils { // FIXME add all the methods from the java class and their tests

  private val oneSuitRegex: Regex = PBNDealTag.getOneSuitRegex.r
  private val oneNonEmptyHandRegex: Regex = PBNDealTag.getNonEmptyHandRegex.r
  private val suitOrder = List(Suit.SPADES, Suit.HEARTS, Suit.DIAMONDS, Suit.CLUBS)

  def getPBNDealTagFromPartialDealTagStringAndDirection(
      partialDealTag: String,
      direction: Direction
  ): PBNDealTag =
    PBNDealTag(s"${direction.getAbbreviation}:${partialDealTag} - - -")

  def handFromPartialDealTag(partialDealTag: String): Option[Hand] = {
    partialDealTag match
      case oneNonEmptyHandRegex(suit1, suit2, suit3, suit4) => {
        val suitStrings = List(suit1, suit2, suit3, suit4)
        val existsInvalidSuitString = suitStrings.exists(suitString => !oneSuitRegex.matches(suitString))
        if (existsInvalidSuitString) None
        else {
          val cards = suitOrder.zip(suitStrings)
            .toSet
            .flatMap((suit, suitString) => getCardsFromSuitAndSuitString(suit, suitString))
          Some(Hand(cards))
        }
      }
      case _ => None
  }

  private def getCardsFromSuitAndSuitString(suit: Suit, suitString: String) =
    suitString.toSet.map(rankChar => Card(suit, Rank.getFromAbbreviation(rankChar).get))

  def partialDealTagFromHand(hand: Hand): String = {
    def getStringFromSetOfCards(cardsSet: Set[Card]): String =
      cardsSet.toVector.sortBy(card => -card.rank.ordinal).map(card => card.rank.getSymbol).mkString
    val stringsGroupedBySuit = hand.allCards
      .groupBy(card => card.suit)
      .map((suit, setOfCards) => (suit -> getStringFromSetOfCards(setOfCards)))
    suitOrder.map(suit => stringsGroupedBySuit.getOrElse(suit, "")).mkString(".")
  }

  def partialDealTagFromHandOption(handOption: Option[Hand]): String = {
    handOption match
      case Some(hand) => partialDealTagFromHand(hand)
      case None       => "-"
  }

}
