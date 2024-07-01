package club.libridge.libridgebackend.core

import org.scalatest.flatspec.AnyFlatSpec
import club.libridge.libridgebackend.core.CompleteHand
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException
import club.libridge.libridgebackend.core.nonpure.PositiveIntegerValidatedBuilder

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
