package scalabridge

import scalabridge.nonpure.ContractFromTextValidatedBuilder
import scala.collection.immutable.SortedSet
import scalabridge.exceptions.TrickAlreadyFullException
import org.junit.jupiter.api.Test

@Test
class TrickTest extends UnitFlatSpec {

  val aceOfSpades = Card(Suit.SPADES, Rank.ACE)
  val tenOfSpades = Card(Suit.SPADES, Rank.TEN)
  val threeOfSpades = Card(Suit.SPADES, Rank.THREE)
  val kingOfClubs = Card(Suit.CLUBS, Rank.KING)
  val anyDirection = Direction.SOUTH
  val emptyTrick = Trick(anyDirection)
  val fourCards = Vector(aceOfSpades, tenOfSpades, threeOfSpades, kingOfClubs)
  val completeTrick = Trick(anyDirection, fourCards)

  "A Trick" should "be constructable from leader" in {
    val trick = Trick(anyDirection)
    assertResult(anyDirection)(trick.getLeader)
    assertResult(anyDirection)(trick.leader)
    assert(trick.isEmpty)
  }

  "A Trick" should "be constructable from leader and cards" in {
    val someCards = Vector(aceOfSpades, tenOfSpades)
    val trick = Trick(anyDirection, someCards)
    assertResult(2)(trick.cards.size)
  }

  "A Trick" should "return the leader" in {
    val trick = Trick(anyDirection)
    assertResult(anyDirection)(trick.getLeader)
  }

  "A Trick" should "return if it is empty" in {
    val emptyTrick = Trick(anyDirection)
    val someCards = Vector(aceOfSpades, tenOfSpades)
    val nonEmptyTrick = Trick(anyDirection, someCards)
    assert(emptyTrick.isEmpty)
    assert(!nonEmptyTrick.isEmpty)
  }

  "A Trick" should "return if it is complete" in {
    val someCards = Vector(aceOfSpades, tenOfSpades)
    val nonEmptyTrick = Trick(anyDirection, someCards)
    assert(!emptyTrick.isComplete)
    assert(!nonEmptyTrick.isComplete)
    assert(completeTrick.isComplete)
  }

  "A Trick:addCard" should "add card until there is 4 cards" in {
    val completeTrick = emptyTrick
      .addCard(aceOfSpades)
      .get
      .addCard(tenOfSpades)
      .get
      .addCard(threeOfSpades)
      .get
      .addCard(kingOfClubs)
      .get
    assertResult(fourCards)(completeTrick.cards)
  }

  "A Trick:addCard" should "return the correct exception when the trick is already full" in {
    val option = completeTrick.addCard(aceOfSpades)
    assert(option.isFailure)
    assert(option.failed.get.isInstanceOf[TrickAlreadyFullException])
  }

  "A Trick:addCardSafe" should "return the same object when the trick is already full" in {
    assertResult(completeTrick)(completeTrick.addCardSafe(aceOfSpades))
  }

  "A Trick:addCardSafe" should "add card while there is less than 4 cards" in {
    val trickWithCard = Trick(anyDirection, Vector(aceOfSpades))
    assertResult(trickWithCard)(Trick(anyDirection).addCardSafe(aceOfSpades))
  }

  "A Trick:getCards" should "return a java List for java interoperability" in {
    val list = completeTrick.getCards

    assert(list.isInstanceOf[java.util.List[Card]])
    val pairs = fourCards.zipWithIndex.map((card, index) => (card -> list.get(index)))
    pairs.foreach((expected, actual) => assertResult(expected)(actual))
  }

  "A Trick:getCardDirectionMap" should "return correctly" in {
    val trick = Trick(anyDirection, fourCards)

    val actualMap = trick.getCardDirectionMap
    val expectedPairs = fourCards.zipWithIndex
      .map((card, index) => (card -> anyDirection.next(index)))

    assert(actualMap.isInstanceOf[java.util.Map[Card, Direction]])
    expectedPairs.foreach((card, direction) => assertResult(direction)(actualMap.get(card)))
  }

  "A Trick" should "return leadSuit from the first card added" in {
    val trick = Trick(anyDirection).addCardSafe(aceOfSpades)
    val leadSuitOption = trick.getLeadSuit
    assert(leadSuitOption.isDefined)
    assertResult(Suit.SPADES)(leadSuitOption.get)
  }

  "A Trick:getLeadSuit" should "return none with no cards" in {
    assert(emptyTrick.getLeadSuit.isEmpty)
  }

  "A Trick:getLastPlayer" should "return the last player of the trick" in {
    val myTrick = Trick(Direction.EAST)
    assertResult(Direction.NORTH)(myTrick.getLastPlayer)
  }

  "A Trick:getWinnerWithoutTrumpSuit" should "return correctly" in {
    val myTrick = Trick(anyDirection, fourCards)
    val winnerOption = myTrick.getWinnerWithoutTrumpSuit
    assert(winnerOption.isDefined)
    assertResult(anyDirection)(myTrick.getWinnerWithoutTrumpSuit.get)
  }

  "A Trick:getWinnerWithoutTrumpSuit" should "return None when the trick is not complete" in {
    assert(emptyTrick.getWinnerWithoutTrumpSuit.isEmpty)
  }

  "A Trick:getWinnerWithTrumpSuit" should "return correctly" in {
    val twoOfHearts = Card(Suit.HEARTS, Rank.TWO)
    val fourCards = Vector(aceOfSpades, twoOfHearts, threeOfSpades, kingOfClubs)
    val myTrick = Trick(Direction.NORTH, fourCards)
    val actualWinners = Suit.values.map(suit => (suit -> myTrick.getWinnerWithTrumpSuit(suit).get))
    val expectedWinners = Map(
      (Suit.CLUBS -> Direction.WEST),
      (Suit.DIAMONDS -> Direction.NORTH),
      (Suit.HEARTS -> Direction.EAST),
      (Suit.SPADES -> Direction.NORTH)
    )
    actualWinners.foreach((suit, actualWinner) => assertResult(expectedWinners(suit))(actualWinner))
  }

  "A Trick:getWinnerWithTrumpSuit" should "return None when the trick is not complete" in {
    assert(emptyTrick.getWinnerWithTrumpSuit(Suit.HEARTS).isEmpty)
  }

  "A Trick:hasCardOf" should "return correctly" in {
    assertResult(false)(emptyTrick.hasCardOf(anyDirection))
    assert(completeTrick.hasCardOf(anyDirection))
    val trickWithOneCard = Trick(Direction.NORTH).addCardSafe(aceOfSpades)
    assert(trickWithOneCard.hasCardOf(Direction.NORTH))
    assertResult(false)(trickWithOneCard.hasCardOf(Direction.EAST))
  }

}
