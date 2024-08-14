package scalabridge

import org.scalatest.flatspec.AnyFlatSpec
import scalabridge.CompleteHand
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException
import scalabridge.nonpure.PositiveIntegerValidatedBuilder

@Test
class EffectiveDuplicateBoardNumberTest extends AnyFlatSpec {
    val eight = PositiveIntegerValidatedBuilder.build(PositiveInteger(8))
    val sixteen = PositiveIntegerValidatedBuilder.build(PositiveInteger(16))
    val seventeen = PositiveIntegerValidatedBuilder.build(PositiveInteger(17))
    "A EffectiveDuplicateBoardNumber" should "give the correct boardNumber" in {
        assertResult(EffectiveDuplicateBoardNumber.EIGHT)(EffectiveDuplicateBoardNumber.fromPositiveInteger(eight))
        assertResult(EffectiveDuplicateBoardNumber.SIXTEEN)(EffectiveDuplicateBoardNumber.fromPositiveInteger(sixteen))
    }
    "A EffectiveDuplicateBoardNumber" should "give the next EffectiveDuplicateBoardNumber" in {
        val boardNumber = EffectiveDuplicateBoardNumber.fromPositiveInteger(eight)
        val next = boardNumber.next
        assertResult(EffectiveDuplicateBoardNumber.NINE)(boardNumber.next)
    }
    "A EffectiveDuplicateBoardNumber" should "give ONE as next from SIXTEEN" in {
        val next = EffectiveDuplicateBoardNumber.fromPositiveInteger(sixteen).next
        assertResult(EffectiveDuplicateBoardNumber.ONE)(next)
    }
    "A EffectiveDuplicateBoardNumber" should "use modulo after sixteen" in {
        val boardNumber = EffectiveDuplicateBoardNumber.fromPositiveInteger(seventeen)
        assertResult(EffectiveDuplicateBoardNumber.ONE)(boardNumber)
    }
  
}
