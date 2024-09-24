package scalabridge

import org.junit.jupiter.api.Test
import scalabridge.nonpure.ContractFromTextValidatedBuilder
import scala.collection.immutable.SortedSet

@Test
class HandTest extends UnitFunSpec {
  private val validHandString = "KJT932.97.942.86"
  private val completeHand = CompleteHand(validHandString)
  private val cardsInHand = completeHand.cards

  val aceOfSpades = Card(Suit.SPADES, Rank.ACE)
  val tenOfSpades = Card(Suit.SPADES, Rank.TEN)
  val threeOfSpades = Card(Suit.SPADES, Rank.THREE)
  val kingOfClubs = Card(Suit.CLUBS, Rank.KING)
  describe("A Hand") {
    it("should be constructable from java list") {
      val javaList: java.util.List[Card] = java.util.List.of(aceOfSpades, tenOfSpades)
      val myHand = Hand(javaList)
      myHand shouldBe Hand(Set(aceOfSpades, tenOfSpades))
    }

    it("should return its cards sorted by its ordering") {
      val someCards = Set(aceOfSpades, tenOfSpades)

      val myHand = Hand(someCards)
      val myHandWithReverseOrdering = Hand(someCards, ordering = DefaultHandOrdering.reverse)
      val sortedDefault = myHand.getCards
      val sortedReverse = myHandWithReverseOrdering.getCards

      sortedDefault.getFirst() shouldBe aceOfSpades
      sortedReverse.getFirst() shouldBe tenOfSpades
    }
    it("should override toString") {
      val myHand = Hand(cardsInHand)
      myHand.toString() shouldBe validHandString
    }
    it("should addCard") {
      val myHand = Hand(Set(aceOfSpades))
      val newHand = myHand.addCard(tenOfSpades)
      val expectedHand = Hand(Set(tenOfSpades, aceOfSpades))
      newHand shouldBe expectedHand
    }
    it("should playCard") {
      val myHand = Hand(Set(aceOfSpades, tenOfSpades))
      val newHand = myHand.playCard(aceOfSpades)
      val expectedHand = Hand(Set(aceOfSpades, tenOfSpades), Seq(aceOfSpades))
      newHand shouldBe expectedHand
    }
    it("should unplayCard") {
      val myHand = Hand(Set(aceOfSpades, tenOfSpades)).playCard(aceOfSpades)
      val newHand = myHand.unplayCard(aceOfSpades)
      val expectedHand = Hand(Set(aceOfSpades, tenOfSpades))
      newHand shouldBe expectedHand
    }
    it("removeOneRandomCard should not remove a played card") {
      val myHand = Hand(Set(aceOfSpades, tenOfSpades)).playCard(aceOfSpades)
      val newHand = myHand.removeOneRandomCard
      val expectedHand = Hand(Set(aceOfSpades), Seq(aceOfSpades))
      newHand shouldBe expectedHand
    }
    it("size should count only unplayed cards") {
      val myHand = Hand(Set(aceOfSpades, tenOfSpades)).playCard(aceOfSpades)
      myHand should have size 1
    }
    it("should return if it contains a card without counting played cards") {
      val myHand = Hand(Set(aceOfSpades, tenOfSpades)).playCard(aceOfSpades)
      myHand.containsCard(aceOfSpades) shouldBe false
      myHand.containsCard(tenOfSpades) shouldBe true
      myHand.containsCard(kingOfClubs) shouldBe false
    }
    it("should return if it has a suit without counting played cards") {
      val myHand = Hand(Set(aceOfSpades, kingOfClubs)).playCard(aceOfSpades)
      myHand.hasSuit(Suit.SPADES) shouldBe false
      myHand.hasSuit(Suit.CLUBS) shouldBe true
      myHand.hasSuit(Suit.HEARTS) shouldBe false
    }
    it("should transform toString using PBN implementation") {
      val myHand = Hand(Set(aceOfSpades, kingOfClubs))
      myHand.toString() shouldBe "A...K"
    }
  }
}
