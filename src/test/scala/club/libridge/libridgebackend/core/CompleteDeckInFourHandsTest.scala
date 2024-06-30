package club.libridge.libridgebackend.core

import org.scalatest.flatspec.AnyFlatSpec
import club.libridge.libridgebackend.core.CompleteHand
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

@Test
class CompleteDeckInFourHandsTest extends AnyFlatSpec {
    val completeHand1 = CompleteHand("86.KT2.K85.Q9742")
    val completeHand2 = CompleteHand("KJT932.97.942.86")
    val completeHand3 = CompleteHand("54.8653.AQJT73.3")
    val completeHand4 = CompleteHand("AQ7.AQJ4.6.AKJT5")
    val lessCards = CompleteHand("86.KT2.K85.Q974")
    val allCompleteHands = Map(
        Direction.NORTH -> completeHand1,
        Direction.EAST -> completeHand2,
        Direction.SOUTH -> completeHand3,
        Direction.WEST -> completeHand4
    )
    val missingAHand = Map(
        Direction.NORTH -> completeHand1,
        Direction.EAST -> completeHand2,
        Direction.SOUTH -> completeHand3
    )
    val oneInvalidHand = Map(
        Direction.NORTH -> completeHand1,
        Direction.EAST -> completeHand2,
        Direction.SOUTH -> completeHand3,
        Direction.WEST -> lessCards
    )
    "A CompleteDeckInFourHands" should "be valid when constructed using valid hands" in {
        assert(CompleteDeckInFourHands(allCompleteHands).getValid().isRight)
    }
    "A CompleteDeckInFourHands" should "not be valid when created with a missing hand." in {
        assert(CompleteDeckInFourHands(missingAHand).getValid().isLeft)
    }
    "A CompleteDeckInFourHands" should "not be valid when created with one invalid hand." in {
        assert(CompleteDeckInFourHands(oneInvalidHand).getValid().isLeft)
    }
    "A valid CompleteDeckInFourHands" should "provide each of the hands" in {
        val subject = CompleteDeckInFourHands(allCompleteHands)
        assertResult(completeHand1)(subject.getHandOf(Direction.NORTH))
        assertResult(completeHand2)(subject.getHandOf(Direction.EAST))
        assertResult(completeHand3)(subject.getHandOf(Direction.SOUTH))
        assertResult(completeHand4)(subject.getHandOf(Direction.WEST))
    }

}
