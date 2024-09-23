package scalabridge.nonpure

import scalabridge.PositiveInteger
import scalabridge.UnitFlatSpec
import org.junit.jupiter.api.Test

@Test
/**
  * Only one of these tests should be enough, as the behavior is defined in the trait.
  */
class ValidatedBuilderTest extends UnitFlatSpec {

  "A PositiveIntegerBuilder" should "build a Valid positive integer" in {
    val subject = PositiveIntegerValidatedBuilder.build(PositiveInteger(1))
  }

  "A PositiveIntegerBuilder" should "throw exception with an invalid positiveInteger" in {
    assertThrows[IllegalArgumentException] {
      PositiveIntegerValidatedBuilder.build(PositiveInteger(-1))
    }
  }

}
