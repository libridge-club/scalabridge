package scalabridge

import org.junit.jupiter.api.Test
import org.scalatest.EitherValues

@Test
class PositiveIntegerTest extends UnitFunSpec with EitherValues {
  describe("A PositiveInteger") {
    it("should be Valid for a positive integer") {
      val subject = PositiveInteger(1)
      assert(subject.getValid().isRight)
    }
    it("should not be Valid and contain IllegalArgumentException for 0") {
      val subject = PositiveInteger(0)
      subject.getValid().left.value.head shouldBe an[IllegalArgumentException]
    }
    it("should not be Valid and contain IllegalArgumentException for -1") {
      val subject = PositiveInteger(-1)
      subject.getValid().left.value.head shouldBe an[IllegalArgumentException]
    }
  }
}
