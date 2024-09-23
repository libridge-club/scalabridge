package scalabridge

import scalabridge.nonpure.ContractFromTextValidatedBuilder
import scala.collection.immutable.SortedSet
import org.junit.jupiter.api.Test

@Test
class HandEvaluationsTest extends UnitFlatSpec {
  private def getHandEvaluations(hand: String) = HandEvaluations(Hand(CompleteHand(hand)))
  private val sampleHand1 = getHandEvaluations("AQJ5.K32..AJ9876")
  private val sampleHand2 = getHandEvaluations("AJ75.K32.Q73.J97")
  private val sampleHand3 = getHandEvaluations("AKJ75.K32.Q73.J7")
  private val sampleHand4 = getHandEvaluations("AKJ75.KQ32.73.J7")
  private val sampleHand5 = getHandEvaluations("AKJ75432.KQ32..7")
  private val sampleHand6 = getHandEvaluations("KQ32.AKJ7542.2.7")

  "HandEvaluations" should "getHCP" in {
    assertResult(15)(sampleHand1.getHCP)
    assertResult(11)(sampleHand2.getHCP)
    assertResult(14)(sampleHand3.getHCP)
  }
  "HandEvaluations" should "getShortestSuitLength" in {
    assertResult(0)(sampleHand1.getShortestSuitLength)
    assertResult(3)(sampleHand2.getShortestSuitLength)
    assertResult(2)(sampleHand3.getShortestSuitLength)
  }
  "HandEvaluations" should "getLongestSuitLength" in {
    assertResult(6)(sampleHand1.getLongestSuitLength)
    assertResult(4)(sampleHand2.getLongestSuitLength)
    assertResult(5)(sampleHand3.getLongestSuitLength)
  }
  "HandEvaluations" should "getNumberOfDoubletonSuits" in {
    assertResult(0)(sampleHand1.getNumberOfDoubletonSuits)
    assertResult(0)(sampleHand2.getNumberOfDoubletonSuits)
    assertResult(1)(sampleHand3.getNumberOfDoubletonSuits)
  }

  "HandEvaluations" should "correctly return hasFiveOrMoreCardsInAMajorSuit" in {
    assertResult(false)(sampleHand1.hasFiveOrMoreCardsInAMajorSuit)
    assertResult(false)(sampleHand2.hasFiveOrMoreCardsInAMajorSuit)
    assertResult(true)(sampleHand3.hasFiveOrMoreCardsInAMajorSuit)
  }

  "HandEvaluations" should "correctly return hasThreeOrMoreCardsInAMinorSuit" in {
    assertResult(true)(sampleHand1.hasThreeOrMoreCardsInAMinorSuit)
    assertResult(true)(sampleHand2.hasThreeOrMoreCardsInAMinorSuit)
    assertResult(true)(sampleHand3.hasThreeOrMoreCardsInAMinorSuit)
    assertResult(false)(sampleHand4.hasThreeOrMoreCardsInAMinorSuit)
  }

  "HandEvaluations" should "correctly return isBalanced" in {
    assertResult(false)(sampleHand1.isBalanced)
    assertResult(true)(sampleHand2.isBalanced)
    assertResult(true)(sampleHand3.isBalanced)
    assertResult(false)(sampleHand4.isBalanced)
  }

  "HandEvaluations" should "correctly return hasEightOrMoreCardsInAnySuit" in {
    assertResult(false)(sampleHand1.hasEightOrMoreCardsInAnySuit)
    assertResult(false)(sampleHand2.hasEightOrMoreCardsInAnySuit)
    assertResult(false)(sampleHand3.hasEightOrMoreCardsInAnySuit)
    assertResult(false)(sampleHand4.hasEightOrMoreCardsInAnySuit)
    assertResult(true)(sampleHand5.hasEightOrMoreCardsInAnySuit)
  }

  "HandEvaluations" should "correctly return hasSevenCardsInLongestSuit" in {
    assertResult(false)(sampleHand1.hasSevenCardsInLongestSuit)
    assertResult(false)(sampleHand2.hasSevenCardsInLongestSuit)
    assertResult(false)(sampleHand3.hasSevenCardsInLongestSuit)
    assertResult(false)(sampleHand4.hasSevenCardsInLongestSuit)
    assertResult(false)(sampleHand5.hasSevenCardsInLongestSuit)
    assertResult(true)(sampleHand6.hasSevenCardsInLongestSuit)
  }

  "HandEvaluations" should "correctly return hasSixCardsInLongestSuit" in {
    assertResult(true)(sampleHand1.hasSixCardsInLongestSuit)
    assertResult(false)(sampleHand2.hasSixCardsInLongestSuit)
    assertResult(false)(sampleHand3.hasSixCardsInLongestSuit)
    assertResult(false)(sampleHand4.hasSixCardsInLongestSuit)
    assertResult(false)(sampleHand5.hasSixCardsInLongestSuit)
    assertResult(false)(sampleHand6.hasSixCardsInLongestSuit)
  }

  "HandEvaluations" should "correctly return hasTwoOutOfThreeHigherCards" in {
    assertResult(false)(sampleHand1.hasTwoOutOfThreeHigherCards(Suit.HEARTS))
    assertResult(false)(sampleHand2.hasTwoOutOfThreeHigherCards(Suit.HEARTS))
    assertResult(false)(sampleHand3.hasTwoOutOfThreeHigherCards(Suit.HEARTS))
    assertResult(true)(sampleHand4.hasTwoOutOfThreeHigherCards(Suit.HEARTS))
    assertResult(true)(sampleHand5.hasTwoOutOfThreeHigherCards(Suit.HEARTS))
    assertResult(true)(sampleHand6.hasTwoOutOfThreeHigherCards(Suit.HEARTS))
  }

  "HandEvaluations" should "correctly return hasThreeOutOfFiveHigherCards" in {
    assertResult(true)(sampleHand1.hasThreeOutOfFiveHigherCards(Suit.SPADES))
    assertResult(false)(sampleHand2.hasThreeOutOfFiveHigherCards(Suit.SPADES))
    assertResult(true)(sampleHand3.hasThreeOutOfFiveHigherCards(Suit.SPADES))
    assertResult(true)(sampleHand4.hasThreeOutOfFiveHigherCards(Suit.SPADES))
    assertResult(true)(sampleHand5.hasThreeOutOfFiveHigherCards(Suit.SPADES))
    assertResult(false)(sampleHand6.hasThreeOutOfFiveHigherCards(Suit.SPADES))
  }

  "HandEvaluations" should "getLongestSuit" in {
    assertResult(Suit.CLUBS)(sampleHand1.getLongestSuit)
    assertResult(Suit.SPADES)(sampleHand2.getLongestSuit)
    assertResult(Suit.SPADES)(sampleHand3.getLongestSuit)
    assertResult(Suit.SPADES)(sampleHand4.getLongestSuit)
    assertResult(Suit.SPADES)(sampleHand5.getLongestSuit)
    assertResult(Suit.HEARTS)(sampleHand6.getLongestSuit)
  }

  "HandEvaluations" should "correctly return hasFourOrMoreCardsInMajorSuitExcludingLongestSuit" in {
    assertResult(true)(sampleHand1.hasFourOrMoreCardsInMajorSuitExcludingLongestSuit)
    assertResult(false)(sampleHand2.hasFourOrMoreCardsInMajorSuitExcludingLongestSuit)
    assertResult(false)(sampleHand3.hasFourOrMoreCardsInMajorSuitExcludingLongestSuit)
    assertResult(true)(sampleHand4.hasFourOrMoreCardsInMajorSuitExcludingLongestSuit)
    assertResult(true)(sampleHand5.hasFourOrMoreCardsInMajorSuitExcludingLongestSuit)
    assertResult(true)(sampleHand6.hasFourOrMoreCardsInMajorSuitExcludingLongestSuit)
  }

  "HandEvaluations" should "getLongestMajor" in {
    assertResult(Suit.SPADES)(sampleHand1.getLongestMajor)
    assertResult(Suit.SPADES)(sampleHand2.getLongestMajor)
    assertResult(Suit.SPADES)(sampleHand3.getLongestMajor)
    assertResult(Suit.SPADES)(sampleHand4.getLongestMajor)
    assertResult(Suit.SPADES)(sampleHand5.getLongestMajor)
    assertResult(Suit.HEARTS)(sampleHand6.getLongestMajor)
  }

  "HandEvaluations" should "getLongestMinor" in {
    assertResult(Suit.CLUBS)(sampleHand1.getLongestMinor)
    assertResult(Suit.CLUBS)(sampleHand2.getLongestMinor)
    assertResult(Suit.DIAMONDS)(sampleHand3.getLongestMinor)
    assertResult(Suit.CLUBS)(sampleHand4.getLongestMinor)
    assertResult(Suit.CLUBS)(sampleHand5.getLongestMinor)
    assertResult(Suit.CLUBS)(sampleHand6.getLongestMinor)
  }

}
