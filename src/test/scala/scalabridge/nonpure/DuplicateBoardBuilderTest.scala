package scalabridge.nonpure

import org.scalatest.flatspec.AnyFlatSpec
import org.junit.jupiter.api.Test
import scalabridge.EffectiveDuplicateBoardNumber
import scalabridge.Direction
import scalabridge.CompleteHand
import scalabridge.PositiveInteger

@Test
class DuplicateBoardBuilderTest extends AnyFlatSpec {
  val boardNumber = 1
  val negativeBoardNumber = -1
  val pbnDealTag = "E:86.KT2.K85.Q9742 KJT932.97.942.86 54.8653.AQJT73.3 AQ7.AQJ4.6.AKJT5"
  val invalidPbnDealTag1 = "X:86.KT2.K85.Q9742 KJT932.97.942.86 54.8653.AQJT73.3 AQ7.AQJ4.6.AKJT5"
  val invalidPbnDealTag2 = "N:P6.KT2.K85.Q9742 KJT932.97.942.86 54.8653.AQJT73.3 AQ7.AQJ4.6.AKJT5"
  val invalidPbnDealTag3 = "N:86.KT2.K85.Q9742 KJT932.97.942.86 54.8653.AQJT73.3"
  val northPbnString = "AQ7.AQJ4.6.AKJT5"
  val northCompleteHand = CompleteHand(northPbnString)
  "A DuplicateBoardBuilder" should "build a DuplicateBoard from int and pbnDealTag" in {
    val subject = DuplicateBoardBuilder.build(boardNumber, pbnDealTag)
    assertResult(PositiveInteger(boardNumber))(subject.number)
    assertResult(northCompleteHand)(subject.hands.getHandOf(Direction.NORTH))
  }
  "A DuplicateBoardBuilder" should "throw exception if something is invalid" in {
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
