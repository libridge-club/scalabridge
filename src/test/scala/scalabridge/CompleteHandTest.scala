package scalabridge

import org.junit.jupiter.api.Test
import org.scalatest.EitherValues
import scalabridge.pbn.PBNUtils

import java.lang.IllegalArgumentException

@Test
class CompleteHandTest extends UnitFunSpec with EitherValues {
  def getHandFromString(handString: String) = PBNUtils.handFromPartialDealTag(handString).get
  val handWithCompleteCards = getHandFromString("A6.KT2.K85.Q9742")
  val completeCards = CompleteHand(handWithCompleteCards)
  val lessCards = CompleteHand(getHandFromString("86.KT2.K85.Q974"))
  val moreCards = CompleteHand(getHandFromString("86.KT2.K85.Q97432"))
  val handWithAceOfSpades = completeCards
  val handWithoutAceOfSpades = CompleteHand(getHandFromString("K6.KT2.K85.Q9742"))
  val aceOfSpades = Card(Suit.SPADES, Rank.ACE)
  describe("A CompleteHand") {
    it("should be valid when it has 13 cards") {
      completeCards.getValid() shouldBe Right(completeCards)
    }
    it("should not be valid when created with a different number of cards.") {
      lessCards.getValid().left.value.head shouldBe an[IllegalArgumentException]
      moreCards.getValid().left.value.head shouldBe an[IllegalArgumentException]
    }
    it("should have its hand accessible") {
      completeCards.hand shouldBe handWithCompleteCards
    }
    describe("containsCard function") {
      it("should return if there is a specific card inside the hand") {
        handWithAceOfSpades.containsCard(aceOfSpades) shouldBe true
        handWithoutAceOfSpades.containsCard(aceOfSpades) shouldBe false
      }
    }
    describe("cards function") {
      it("should return the correct set of cards") {
        val actualSet = completeCards.cards
        val expectedSet = Set(
          Card(Suit.SPADES, Rank.ACE),
          Card(Suit.SPADES, Rank.SIX),
          Card(Suit.HEARTS, Rank.KING),
          Card(Suit.HEARTS, Rank.TEN),
          Card(Suit.HEARTS, Rank.TWO),
          Card(Suit.DIAMONDS, Rank.KING),
          Card(Suit.DIAMONDS, Rank.EIGHT),
          Card(Suit.DIAMONDS, Rank.FIVE),
          Card(Suit.CLUBS, Rank.QUEEN),
          Card(Suit.CLUBS, Rank.NINE),
          Card(Suit.CLUBS, Rank.SEVEN),
          Card(Suit.CLUBS, Rank.FOUR),
          Card(Suit.CLUBS, Rank.TWO)
        )
        actualSet shouldBe expectedSet
      }
    }
  }
}
