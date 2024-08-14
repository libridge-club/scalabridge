package scalabridge

import org.scalatest.flatspec.AnyFlatSpec
import org.junit.jupiter.api.Test

@Test
class PositiveIntegerTest extends AnyFlatSpec {
    "A PositiveInteger" should "be Valid for a positive integer" in {
        val subject = PositiveInteger(1)
        assert(subject.getValid().isRight)
    }
    "A PositiveInteger" should "not be Valid for 0" in {
        val subject = PositiveInteger(0)
        assert(subject.getValid().isLeft)
    }
    "A PositiveInteger" should "not be Valid for -1" in {
        val subject = PositiveInteger(-1)
        assert(subject.getValid().isLeft)
    }
    "A PositiveInteger" should "contain IllegalArgumentException when invalid" in {
        val subject = PositiveInteger(-1)
        val headException = subject.getValid() match {
            case Left(exceptions) => exceptions.head
            case Right(value) => fail()
        }
        assert(headException.isInstanceOf[IllegalArgumentException])
    }
}
