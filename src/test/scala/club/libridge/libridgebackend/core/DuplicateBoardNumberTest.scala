package club.libridge.libridgebackend.core

import org.scalatest.flatspec.AnyFlatSpec
import club.libridge.libridgebackend.core.CompleteHand
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException
import club.libridge.libridgebackend.core.nonpure.PositiveIntegerValidatedBuilder

@Test
class DuplicateBoardNumberTest extends AnyFlatSpec {
    val eight = PositiveIntegerValidatedBuilder.build(PositiveInteger(8))
    val sixteen = PositiveIntegerValidatedBuilder.build(PositiveInteger(16))
    val seventeen = PositiveIntegerValidatedBuilder.build(PositiveInteger(17))
    "A DuplicateBoardNumber" should "give the correct boardNumber" in {
        assertResult(DuplicateBoardNumber.EIGHT)(DuplicateBoardNumber.fromPositiveInteger(eight))
        assertResult(DuplicateBoardNumber.SIXTEEN)(DuplicateBoardNumber.fromPositiveInteger(sixteen))
    }
    "A DuplicateBoardNumber" should "give the next DuplicateBoardNumber" in {
        val boardNumber = DuplicateBoardNumber.fromPositiveInteger(eight)
        val next = boardNumber.next
        assertResult(DuplicateBoardNumber.NINE)(boardNumber.next)
    }
    "A DuplicateBoardNumber" should "give ONE as next from SIXTEEN" in {
        val next = DuplicateBoardNumber.fromPositiveInteger(sixteen).next
        assertResult(DuplicateBoardNumber.ONE)(next)
    }
    "A DuplicateBoardNumber" should "use modulo after sixteen" in {
        val boardNumber = DuplicateBoardNumber.fromPositiveInteger(seventeen)
        assertResult(DuplicateBoardNumber.ONE)(boardNumber)
    }
  
}
