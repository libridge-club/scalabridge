package scalabridge

import scalabridge.CompleteHand
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException
import org.scalatest.EitherValues

@Test
class CompleteDeckInFourHandsTest extends UnitFunSpec with EitherValues {
  val completeHand1 = CompleteHand("86.KT2.K85.Q9742")
  val completeHand2 = CompleteHand("KJT932.97.942.86")
  val completeHand3 = CompleteHand("54.8653.AQJT73.3")
  val completeHand4 = CompleteHand("AQ7.AQJ4.6.AKJT5")
  val lessCards = CompleteHand("86.KT2.K85.Q974")
  val allCompleteHands: Map[Direction, CompleteHand] = Map(
    Direction.NORTH -> completeHand1,
    Direction.EAST -> completeHand2,
    Direction.SOUTH -> completeHand3,
    Direction.WEST -> completeHand4
  )
  val missingAHand: Map[Direction, CompleteHand] = Map(
    Direction.NORTH -> completeHand1,
    Direction.EAST -> completeHand2,
    Direction.SOUTH -> completeHand3
  )
  val oneInvalidHand: Map[Direction, CompleteHand] = Map(
    Direction.NORTH -> completeHand1,
    Direction.EAST -> completeHand2,
    Direction.SOUTH -> completeHand3,
    Direction.WEST -> lessCards
  )
  describe("A CompleteDeckInFourHands") {
    it("should be valid when constructed using valid hands") {
      CompleteDeckInFourHands(allCompleteHands)
        .getValid()
        .value shouldBe CompleteDeckInFourHands(allCompleteHands)
    }
    it("should not be valid when created with a missing hand.") {
      CompleteDeckInFourHands(missingAHand)
        .getValid()
        .left
        .value
        .head shouldBe an[IllegalArgumentException]
    }
    it("should not be valid when created with one invalid hand.") {
      CompleteDeckInFourHands(oneInvalidHand)
        .getValid()
        .left
        .value
        .head shouldBe an[IllegalArgumentException]
    }
    it("should provide each of the hands when valid") {
      val subject = CompleteDeckInFourHands(allCompleteHands)
      subject.getHandOf(Direction.NORTH) shouldBe completeHand1
      subject.getHandOf(Direction.EAST) shouldBe completeHand2
      subject.getHandOf(Direction.SOUTH) shouldBe completeHand3
      subject.getHandOf(Direction.WEST) shouldBe completeHand4
    }
  }
}
