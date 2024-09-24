package scalabridge

import scalabridge.nonpure.ContractFromTextValidatedBuilder
import scala.collection.immutable.SortedSet
import org.junit.jupiter.api.Test

@Test
class HandEvaluationsTest extends UnitFunSpec {
  private def getHandEvaluations(hand: String) = HandEvaluations(Hand(CompleteHand(hand)))
  private val sampleHand1 = getHandEvaluations("AQJ5.K32..AJ9876")
  private val sampleHand2 = getHandEvaluations("AJ75.K32.Q73.J97")
  private val sampleHand3 = getHandEvaluations("AKJ75.K32.Q73.J7")
  private val sampleHand4 = getHandEvaluations("AKJ75.KQ32.73.J7")
  private val sampleHand5 = getHandEvaluations("AKJ75432.KQ32..7")
  private val sampleHand6 = getHandEvaluations("KQ32.AKJ7542.2.7")

  describe("HandEvaluations") {
    it("should getHCP") {
      sampleHand1.getHCP shouldBe 15
      sampleHand2.getHCP shouldBe 11
      sampleHand3.getHCP shouldBe 14
    }
    it("should getShortestSuitLength") {
      sampleHand1.getShortestSuitLength shouldBe 0
      sampleHand2.getShortestSuitLength shouldBe 3
      sampleHand3.getShortestSuitLength shouldBe 2
    }
    it("should getLongestSuitLength") {
      sampleHand1.getLongestSuitLength shouldBe 6
      sampleHand2.getLongestSuitLength shouldBe 4
      sampleHand3.getLongestSuitLength shouldBe 5
    }
    it("should getNumberOfDoubletonSuits") {
      sampleHand1.getNumberOfDoubletonSuits shouldBe 0
      sampleHand2.getNumberOfDoubletonSuits shouldBe 0
      sampleHand3.getNumberOfDoubletonSuits shouldBe 1
    }

    it("should correctly return hasFiveOrMoreCardsInAMajorSuit") {
      sampleHand1.hasFiveOrMoreCardsInAMajorSuit shouldBe false
      sampleHand2.hasFiveOrMoreCardsInAMajorSuit shouldBe false
      sampleHand3.hasFiveOrMoreCardsInAMajorSuit shouldBe true
    }

    it("should correctly return hasThreeOrMoreCardsInAMinorSuit") {
      sampleHand1.hasThreeOrMoreCardsInAMinorSuit shouldBe true
      sampleHand2.hasThreeOrMoreCardsInAMinorSuit shouldBe true
      sampleHand3.hasThreeOrMoreCardsInAMinorSuit shouldBe true
      sampleHand4.hasThreeOrMoreCardsInAMinorSuit shouldBe false
    }

    it("should correctly return isBalanced") {
      sampleHand1.isBalanced shouldBe false
      sampleHand2.isBalanced shouldBe true
      sampleHand3.isBalanced shouldBe true
      sampleHand4.isBalanced shouldBe false
    }

    it("should correctly return hasEightOrMoreCardsInAnySuit") {
      sampleHand1.hasEightOrMoreCardsInAnySuit shouldBe false
      sampleHand2.hasEightOrMoreCardsInAnySuit shouldBe false
      sampleHand3.hasEightOrMoreCardsInAnySuit shouldBe false
      sampleHand4.hasEightOrMoreCardsInAnySuit shouldBe false
      sampleHand5.hasEightOrMoreCardsInAnySuit shouldBe true
    }

    it("should correctly return hasSevenCardsInLongestSuit") {
      sampleHand1.hasSevenCardsInLongestSuit shouldBe false
      sampleHand2.hasSevenCardsInLongestSuit shouldBe false
      sampleHand3.hasSevenCardsInLongestSuit shouldBe false
      sampleHand4.hasSevenCardsInLongestSuit shouldBe false
      sampleHand5.hasSevenCardsInLongestSuit shouldBe false
      sampleHand6.hasSevenCardsInLongestSuit shouldBe true
    }

    it("should correctly return hasSixCardsInLongestSuit") {
      sampleHand1.hasSixCardsInLongestSuit shouldBe true
      sampleHand2.hasSixCardsInLongestSuit shouldBe false
      sampleHand3.hasSixCardsInLongestSuit shouldBe false
      sampleHand4.hasSixCardsInLongestSuit shouldBe false
      sampleHand5.hasSixCardsInLongestSuit shouldBe false
      sampleHand6.hasSixCardsInLongestSuit shouldBe false
    }

    it("should correctly return hasTwoOutOfThreeHigherCards") {
      sampleHand1.hasTwoOutOfThreeHigherCards(Suit.HEARTS) shouldBe false
      sampleHand2.hasTwoOutOfThreeHigherCards(Suit.HEARTS) shouldBe false
      sampleHand3.hasTwoOutOfThreeHigherCards(Suit.HEARTS) shouldBe false
      sampleHand4.hasTwoOutOfThreeHigherCards(Suit.HEARTS) shouldBe true
      sampleHand5.hasTwoOutOfThreeHigherCards(Suit.HEARTS) shouldBe true
      sampleHand6.hasTwoOutOfThreeHigherCards(Suit.HEARTS) shouldBe true
    }

    it("should correctly return hasThreeOutOfFiveHigherCards") {
      sampleHand1.hasThreeOutOfFiveHigherCards(Suit.SPADES) shouldBe true
      sampleHand2.hasThreeOutOfFiveHigherCards(Suit.SPADES) shouldBe false
      sampleHand3.hasThreeOutOfFiveHigherCards(Suit.SPADES) shouldBe true
      sampleHand4.hasThreeOutOfFiveHigherCards(Suit.SPADES) shouldBe true
      sampleHand5.hasThreeOutOfFiveHigherCards(Suit.SPADES) shouldBe true
      sampleHand6.hasThreeOutOfFiveHigherCards(Suit.SPADES) shouldBe false
    }

    it("should getLongestSuit") {
      sampleHand1.getLongestSuit shouldBe Suit.CLUBS
      sampleHand2.getLongestSuit shouldBe Suit.SPADES
      sampleHand3.getLongestSuit shouldBe Suit.SPADES
      sampleHand4.getLongestSuit shouldBe Suit.SPADES
      sampleHand5.getLongestSuit shouldBe Suit.SPADES
      sampleHand6.getLongestSuit shouldBe Suit.HEARTS
    }

    it("should correctly return hasFourOrMoreCardsInMajorSuitExcludingLongestSuit") {
      sampleHand1.hasFourOrMoreCardsInMajorSuitExcludingLongestSuit shouldBe true
      sampleHand2.hasFourOrMoreCardsInMajorSuitExcludingLongestSuit shouldBe false
      sampleHand3.hasFourOrMoreCardsInMajorSuitExcludingLongestSuit shouldBe false
      sampleHand4.hasFourOrMoreCardsInMajorSuitExcludingLongestSuit shouldBe true
      sampleHand5.hasFourOrMoreCardsInMajorSuitExcludingLongestSuit shouldBe true
      sampleHand6.hasFourOrMoreCardsInMajorSuitExcludingLongestSuit shouldBe true
    }

    it("should getLongestMajor") {
      sampleHand1.getLongestMajor shouldBe Suit.SPADES
      sampleHand2.getLongestMajor shouldBe Suit.SPADES
      sampleHand3.getLongestMajor shouldBe Suit.SPADES
      sampleHand4.getLongestMajor shouldBe Suit.SPADES
      sampleHand5.getLongestMajor shouldBe Suit.SPADES
      sampleHand6.getLongestMajor shouldBe Suit.HEARTS
    }

    it("should getLongestMinor") {
      sampleHand1.getLongestMinor shouldBe Suit.CLUBS
      sampleHand2.getLongestMinor shouldBe Suit.CLUBS
      sampleHand3.getLongestMinor shouldBe Suit.DIAMONDS
      sampleHand4.getLongestMinor shouldBe Suit.CLUBS
      sampleHand5.getLongestMinor shouldBe Suit.CLUBS
      sampleHand6.getLongestMinor shouldBe Suit.CLUBS
    }
  }
}
