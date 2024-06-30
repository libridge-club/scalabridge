package club.libridge.libridgebackend.core.nonpure

import org.scalatest.flatspec.AnyFlatSpec
import org.junit.jupiter.api.Test
import club.libridge.libridgebackend.core.DuplicateBoardNumber
import club.libridge.libridgebackend.core.Direction
import club.libridge.libridgebackend.core.CompleteHand

@Test
class DuplicateBoardValidatedBuilderTest extends AnyFlatSpec {
    val boardNumber = 1
    val negativeBoardNumber = -1
    val pbnDealTag = "E:86.KT2.K85.Q9742 KJT932.97.942.86 54.8653.AQJT73.3 AQ7.AQJ4.6.AKJT5"
    val invalidPbnDealTag1 = "X:86.KT2.K85.Q9742 KJT932.97.942.86 54.8653.AQJT73.3 AQ7.AQJ4.6.AKJT5"
    val invalidPbnDealTag2 = "N:P6.KT2.K85.Q9742 KJT932.97.942.86 54.8653.AQJT73.3 AQ7.AQJ4.6.AKJT5"
    val invalidPbnDealTag3 = "N:86.KT2.K85.Q9742 KJT932.97.942.86 54.8653.AQJT73.3"
    val northPbnString = "AQ7.AQJ4.6.AKJT5"
    val northCompleteHand = CompleteHand(northPbnString)
    "A DuplicateBoardValidatedBuilder" should "build a DuplicateBoard from int and pbnDealTag" in {
        val subject = DuplicateBoardValidatedBuilder.build(boardNumber,pbnDealTag)
        assertResult(DuplicateBoardNumber.ONE)(subject.number)
        assertResult(northCompleteHand)(subject.hands.getHandOf(Direction.NORTH))
    }
    "A DuplicateBoardValidatedBuilder" should "throw exception if something is invalid" in {
        assertThrows[IllegalArgumentException] {
            DuplicateBoardValidatedBuilder.build(negativeBoardNumber,pbnDealTag)
        }
        assertThrows[IllegalArgumentException] {
            DuplicateBoardValidatedBuilder.build(boardNumber,invalidPbnDealTag1)
        }
        assertThrows[IllegalArgumentException] {
            DuplicateBoardValidatedBuilder.build(boardNumber,invalidPbnDealTag2)
        }
        assertThrows[IllegalArgumentException] {
            DuplicateBoardValidatedBuilder.build(boardNumber,invalidPbnDealTag3)
        }
    }
}
