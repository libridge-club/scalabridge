package scalabridge

import scalabridge.CompleteHand
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException
import scalabridge.nonpure.PositiveIntegerValidatedBuilder

@Test
class EffectiveDuplicateBoardNumberTest extends UnitFunSpec {
  val eight = PositiveIntegerValidatedBuilder.build(PositiveInteger(8))
  val sixteen = PositiveIntegerValidatedBuilder.build(PositiveInteger(16))
  val seventeen = PositiveIntegerValidatedBuilder.build(PositiveInteger(17))
  describe("An EffectiveDuplicateBoardNumber") {
    it("should give the correct boardNumber") {
      EffectiveDuplicateBoardNumber.fromPositiveInteger(
        eight
      ) shouldBe EffectiveDuplicateBoardNumber.EIGHT
      EffectiveDuplicateBoardNumber.fromPositiveInteger(
        sixteen
      ) shouldBe EffectiveDuplicateBoardNumber.SIXTEEN
    }
    it("should give the next EffectiveDuplicateBoardNumber") {
      val boardEight = EffectiveDuplicateBoardNumber.fromPositiveInteger(eight)
      boardEight.next shouldBe EffectiveDuplicateBoardNumber.NINE
    }
    it("should give ONE as next from SIXTEEN") {
      val boardSixteen = EffectiveDuplicateBoardNumber.fromPositiveInteger(sixteen)
      boardSixteen.next shouldBe EffectiveDuplicateBoardNumber.ONE
    }
    it("should use modulo after sixteen") {
      val boardSeventeen = EffectiveDuplicateBoardNumber.fromPositiveInteger(seventeen)
      boardSeventeen shouldBe EffectiveDuplicateBoardNumber.ONE
    }
  }
}
