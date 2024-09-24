package scalabridge

import scalabridge.CompleteHand
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException
import scalabridge.nonpure.PositiveIntegerValidatedBuilder

@Test
class DuplicateBoardTest extends UnitFunSpec {
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
  describe("A DuplicateBoard") {
    it("should get the correct dealer") {
      val dealerForBoard15 = Direction.SOUTH
      subject.getDealer() shouldBe dealerForBoard15
    }
    it("should get the correct vulnerability") {
      subject.isVulnerable(Side.NORTHSOUTH) shouldBe true
      subject.isVulnerable(Side.EASTWEST) shouldBe false
    }
    it("should get hand of a direction") {
      subject.getHandOf(Direction.EAST) shouldBe completeHand2
    }
  }
}
