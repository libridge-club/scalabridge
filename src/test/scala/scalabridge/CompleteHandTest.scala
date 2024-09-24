package scalabridge

import scalabridge.CompleteHand
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException
import org.scalatest.EitherValues

@Test
class CompleteHandTest extends UnitFunSpec with EitherValues {
  val completeCards = "A6.KT2.K85.Q9742"
  val lessCards = "86.KT2.K85.Q974"
  val moreCards = "86.KT2.K85.Q97432"
  val completeCardsWrongChars = "8F.KT2.K8U.Q9742"
  val completeCardsWrongChars2 = "P6.KT2.K85.Q9742"
  val handWithAceOfSpades = completeCards
  val handWithoutAceOfSpades = "K6.KT2.K85.Q9742"
  val aceOfSpades = Card(Suit.SPADES, Rank.ACE)
  describe("A CompleteHand") {
    it("should be valid when constructed using a pbnString") {
      assert(CompleteHand(completeCards).getValid().isRight)
    }
    it("should not be valid when created with a different number of cards.") {
      CompleteHand(lessCards).getValid().left.value.head shouldBe an[IllegalArgumentException]
      CompleteHand(moreCards).getValid().left.value.head shouldBe an[IllegalArgumentException]
    }
    it("should not be valid when created with wrong characters for ranks.") {
      CompleteHand(completeCardsWrongChars)
        .getValid()
        .left
        .value
        .head shouldBe an[IllegalArgumentException]
      CompleteHand(completeCardsWrongChars2)
        .getValid()
        .left
        .value
        .head shouldBe an[IllegalArgumentException]
    }
    describe("hasCard function") {
      it("should return if there is a specific card inside the hand") {
        assert(CompleteHand(handWithAceOfSpades).hasCard(aceOfSpades))
        assert(!CompleteHand(handWithoutAceOfSpades).hasCard(aceOfSpades))
      }
    }
    describe("cards function") {
      it("should return the correct set of cards") {
        val actualSet = CompleteHand(completeCards).cards
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
