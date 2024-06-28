package club.libridge.libridgebackend.core

import org.scalatest.flatspec.AnyFlatSpec
import club.libridge.libridgebackend.core.CompleteHand
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

@Test
class CompleteHandTest extends AnyFlatSpec {
    val completeCards = "A6.KT2.K85.Q9742"
    val lessCards = "86.KT2.K85.Q974"
    val moreCards = "86.KT2.K85.Q97432"
    val completeCardsWrongChars = "8F.KT2.K8U.Q9742"
    val handWithAceOfSpades = completeCards
    val handWithoutAceOfSpades = "K6.KT2.K85.Q9742"
    val aceOfSpades = new Card(Suit.SPADES, Rank.ACE)
    "A HandScala" should "be constructed using a pbnString" in {
        new CompleteHand(completeCards)
    }
    "A CompleteHand" should "throw exception when created with a different number of cards." in {
        assertThrows[IllegalArgumentException] {
            new CompleteHand(lessCards)
        }
        assertThrows[IllegalArgumentException] {
            new CompleteHand(moreCards)
        }
    }
    "A CompleteHand" should "throw exception when created with wrong characters for ranks." in {
        assertThrows[IllegalArgumentException] {
            new CompleteHand(completeCardsWrongChars)
        }
    }
    "A CompleteHand_hasCard" should "return if there is a specific card inside the hand" in {
        val hand1 = new CompleteHand(handWithAceOfSpades)
        assert(hand1.hasCard(aceOfSpades))
        val hand2 = new CompleteHand(handWithoutAceOfSpades)
        assert(!hand2.hasCard(aceOfSpades))
    }

}
