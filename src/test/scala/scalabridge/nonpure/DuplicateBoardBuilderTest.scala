package scalabridge.nonpure

import scalabridge.EffectiveDuplicateBoardNumber
import scalabridge.Direction
import scalabridge.CompleteHand
import scalabridge.PositiveInteger
import org.junit.jupiter.api.Test
import scalabridge.UnitFunSpec

@Test
class DuplicateBoardBuilderTest extends UnitFunSpec {
  val boardNumber = 1
  val negativeBoardNumber = -1
  val pbnDealTag = "E:86.KT2.K85.Q9742 KJT932.97.942.86 54.8653.AQJT73.3 AQ7.AQJ4.6.AKJT5"
  val invalidPbnDealTag1 = "X:86.KT2.K85.Q9742 KJT932.97.942.86 54.8653.AQJT73.3 AQ7.AQJ4.6.AKJT5"
  val invalidPbnDealTag2 = "N:P6.KT2.K85.Q9742 KJT932.97.942.86 54.8653.AQJT73.3 AQ7.AQJ4.6.AKJT5"
  val invalidPbnDealTag3 = "N:86.KT2.K85.Q9742 KJT932.97.942.86 54.8653.AQJT73.3"
  val northPbnString = "AQ7.AQJ4.6.AKJT5"
  val northCompleteHand = CompleteHand(northPbnString)

  describe("A DuplicateBoardBuilder") {
    describe("when all arguments are valid") {
      it("should build a DuplicateBoard with the correct values") {
        val subject = DuplicateBoardBuilder.build(boardNumber, pbnDealTag)
        subject.number shouldBe PositiveInteger(boardNumber)
        subject.hands.getHandOf(Direction.NORTH) shouldBe northCompleteHand

      }
    }
    describe("when some argument is invalid") {
      it("should throw an IllegalArgumentException") {
        assertThrows[IllegalArgumentException] {
          DuplicateBoardBuilder.build(negativeBoardNumber, pbnDealTag)
        }
        assertThrows[IllegalArgumentException] {
          DuplicateBoardBuilder.build(boardNumber, invalidPbnDealTag1)
        }
        assertThrows[IllegalArgumentException] {
          DuplicateBoardBuilder.build(boardNumber, invalidPbnDealTag2)
        }
        assertThrows[IllegalArgumentException] {
          DuplicateBoardBuilder.build(boardNumber, invalidPbnDealTag3)
        }
      }
    }

  }
}
