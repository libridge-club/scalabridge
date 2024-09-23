package scalabridge

import scalabridge.CompleteHand
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException
import scalabridge.nonpure.PositiveIntegerValidatedBuilder

@Test
class DuplicateBoardTest extends UnitFlatSpec {
  val completeHand1 = CompleteHand("86.KT2.K85.Q9742")
  val completeHand2 = CompleteHand("KJT932.97.942.86")
  val completeHand3 = CompleteHand("54.8653.AQJT73.3")
  val completeHand4 = CompleteHand("AQ7.AQJ4.6.AKJT5")
  val allCompleteHands: Map[Direction, CompleteHand] = Map(
    Direction.NORTH -> completeHand1,
    Direction.EAST -> completeHand2,
    Direction.SOUTH -> completeHand3,
    Direction.WEST -> completeHand4
  )
  val board15 = PositiveIntegerValidatedBuilder.build(PositiveInteger(15))
  val hands = CompleteDeckInFourHands(allCompleteHands)
  val subject = DuplicateBoard(board15, hands)
  "A DuplicateBoard" should "get the correct dealer" in {
    val dealerForBoard15 = Direction.SOUTH
    assertResult(dealerForBoard15)(subject.getDealer())
  }
  "A DuplicateBoard" should "get the correct vulnerability" in {
    assertResult(true)(subject.isVulnerable(Side.NORTHSOUTH))
    assertResult(false)(subject.isVulnerable(Side.EASTWEST))
  }
  "A DuplicateBoard" should "get hand of a direction" in {
    assertResult(completeHand2)(subject.getHandOf(Direction.EAST))
  }
}
