package scalabridge

import scalabridge.nonpure.ContractFromTextValidatedBuilder
import scala.collection.immutable.SortedSet
import scalabridge.exceptions.TrickAlreadyFullException
import org.junit.jupiter.api.Test

@Test
class TrickTest extends UnitFunSpec {

  val aceOfSpades = Card(Suit.SPADES, Rank.ACE)
  val tenOfSpades = Card(Suit.SPADES, Rank.TEN)
  val threeOfSpades = Card(Suit.SPADES, Rank.THREE)
  val kingOfClubs = Card(Suit.CLUBS, Rank.KING)
  val anyDirection = Direction.SOUTH
  val emptyTrick = Trick(anyDirection)
  val fourCards = Vector(aceOfSpades, tenOfSpades, threeOfSpades, kingOfClubs)
  val completeTrick = Trick(anyDirection, fourCards)

  describe("A Trick") {
    it("should be constructable from leader") {
      val trick = Trick(anyDirection)
      trick.getLeader shouldBe anyDirection
      trick.leader shouldBe anyDirection
      trick shouldBe empty
    }

    it("should be constructable from leader and cards") {
      val someCards = Vector(aceOfSpades, tenOfSpades)
      val trick = Trick(anyDirection, someCards)
      trick.cards should have size 2
    }

    it("should return the leader") {
      val trick = Trick(anyDirection)
      trick.getLeader shouldBe anyDirection
    }

    it("should return if it is empty") {
      val emptyTrick = Trick(anyDirection)
      val someCards = Vector(aceOfSpades, tenOfSpades)
      val nonEmptyTrick = Trick(anyDirection, someCards)
      emptyTrick shouldBe empty
      nonEmptyTrick should not be empty
    }

    it("should return if it is complete") {
      val someCards = Vector(aceOfSpades, tenOfSpades)
      val nonEmptyTrick = Trick(anyDirection, someCards)
      assert(!emptyTrick.isComplete)
      assert(!nonEmptyTrick.isComplete)
      assert(completeTrick.isComplete)
    }

    describe("addCard") {
      it("should add card until there is 4 cards") {
        val completeTrick = emptyTrick
          .addCard(aceOfSpades)
          .get
          .addCard(tenOfSpades)
          .get
          .addCard(threeOfSpades)
          .get
          .addCard(kingOfClubs)
          .get
        completeTrick.cards shouldBe fourCards
      }

      it("should return the correct exception when the trick is already full") {
        val option = completeTrick.addCard(aceOfSpades)
        assert(option.isFailure)
        option.failed.get shouldBe an[TrickAlreadyFullException]
      }
    }

    describe("addCardSafe") {
      it("should return the same object when the trick is already full") {
        completeTrick.addCardSafe(aceOfSpades) shouldBe completeTrick
      }

      it("should add card while there is less than 4 cards") {
        val trickWithCard = Trick(anyDirection, Vector(aceOfSpades))
        Trick(anyDirection).addCardSafe(aceOfSpades) shouldBe trickWithCard
      }
    }

    it("getCards should return a java List for java interoperability") {
      val list = completeTrick.getCards

      list shouldBe a[java.util.List[?]]
      val pairs = fourCards.zipWithIndex.map((card, index) => (card -> list.get(index)))
      pairs.foreach((expected, actual) => actual shouldBe expected)
    }

    it("getCardDirectionMap should return correctly") {
      val trick = Trick(anyDirection, fourCards)

      val actualMap = trick.getCardDirectionMap
      val expectedPairs = fourCards.zipWithIndex
        .map((card, index) => (card -> anyDirection.next(index)))

      actualMap shouldBe a[java.util.Map[?, ?]]
      expectedPairs.foreach((card, direction) => actualMap.get(card) shouldBe direction)
    }

    it("should return leadSuit from the first card added") {
      val trick = Trick(anyDirection).addCardSafe(aceOfSpades)
      val leadSuitOption = trick.getLeadSuit
      leadSuitOption shouldBe Some(Suit.SPADES)
    }

    it("getLeadSuit should return none with no cards") {
      emptyTrick.getLeadSuit shouldBe empty
    }

    it("getLastPlayer should return the last player of the trick") {
      val myTrick = Trick(Direction.EAST)
      myTrick.getLastPlayer shouldBe Direction.NORTH
    }

    describe("getWinnerWithoutTrumpSuit") {
      it("should return correctly") {
        val myTrick = Trick(anyDirection, fourCards)
        val winnerOption = myTrick.getWinnerWithoutTrumpSuit
        winnerOption shouldBe Some(anyDirection)
      }

      it("should return None when the trick is not complete") {
        emptyTrick.getWinnerWithoutTrumpSuit shouldBe empty
      }
    }

    describe("getWinnerWithTrumpSuit") {
      it("should return correctly") {
        val twoOfHearts = Card(Suit.HEARTS, Rank.TWO)
        val fourCards = Vector(aceOfSpades, twoOfHearts, threeOfSpades, kingOfClubs)
        val myTrick = Trick(Direction.NORTH, fourCards)
        val actualWinners =
          Suit.values.map(suit => (suit -> myTrick.getWinnerWithTrumpSuit(suit).get))
        val expectedWinners = Map(
          (Suit.CLUBS -> Direction.WEST),
          (Suit.DIAMONDS -> Direction.NORTH),
          (Suit.HEARTS -> Direction.EAST),
          (Suit.SPADES -> Direction.NORTH)
        )
        actualWinners.foreach((suit, actualWinner) => actualWinner shouldBe expectedWinners(suit))
      }

      it("should return None when the trick is not complete") {
        emptyTrick.getWinnerWithTrumpSuit(Suit.HEARTS) shouldBe empty
      }
    }

    it("hasCardOf should return correctly") {
      emptyTrick.hasCardOf(anyDirection) shouldBe false
      assert(completeTrick.hasCardOf(anyDirection))
      val trickWithOneCard = Trick(Direction.NORTH).addCardSafe(aceOfSpades)
      assert(trickWithOneCard.hasCardOf(Direction.NORTH))
      trickWithOneCard.hasCardOf(Direction.EAST) shouldBe false
    }

  }
}
