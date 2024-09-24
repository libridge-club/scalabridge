package scalabridge.nonpure

import scalabridge.PositiveInteger
import org.junit.jupiter.api.Test
import scalabridge.UnitFunSpec

@Test
/**
  * Only one of these tests should be enough, as the behavior is defined in the trait.
  */
class ValidatedBuilderTest extends UnitFunSpec {

  describe("A PositiveIntegerBuilder") {
    describe("when all arguments are valid") {
      it("should build a Valid positive integer") {
        val subject = PositiveIntegerValidatedBuilder.build(PositiveInteger(1))
      }
    }
    describe("when some arguments are invalid") {
      it("should throw exception with the invalid positiveInteger") {
        assertThrows[IllegalArgumentException] {
          PositiveIntegerValidatedBuilder.build(PositiveInteger(-1))
        }
      }
    }
  }

}
