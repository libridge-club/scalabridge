package scalabridge

import org.junit.jupiter.api.Test
import scalabridge.nonpure.PositiveIntegerValidatedBuilder
import scalabridge.pbn.PBNUtils

@Test
class DuplicateBoardTest extends UnitFunSpec {
  def getCompleteHandFromPartialDealString(partialDealString: String) =
    CompleteHand(PBNUtils.handFromPartialDealTag(partialDealString).get)
  val completeHand1 = getCompleteHandFromPartialDealString("86.KT2.K85.Q9742")
  val completeHand2 = getCompleteHandFromPartialDealString("KJT932.97.942.86")
  val completeHand3 = getCompleteHandFromPartialDealString("54.8653.AQJT73.3")
  val completeHand4 = getCompleteHandFromPartialDealString("AQ7.AQJ4.6.AKJT5")
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
