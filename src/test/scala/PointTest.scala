import org.scalatest.flatspec.AnyFlatSpec
import mypackage.Point
import org.junit.jupiter.api.Test

@Test
class PointTest extends AnyFlatSpec {
  "A Point" should "have toString" in {
    val point = new Point(1,2)
    assert(point.toString() === "(1, 2)")
  }
}
