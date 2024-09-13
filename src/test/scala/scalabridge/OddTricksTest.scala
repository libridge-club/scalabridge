package scalabridge

import org.scalatest.flatspec.AnyFlatSpec
import org.junit.jupiter.api.Test
import scala.util.Success
import scalabridge.exceptions.OddTricksException

@Test
class OddTricksTest extends AnyFlatSpec {
  "An OddTricks" should "be comparable" in {
    assert(OddTricks.FIVE.compareTo(OddTricks.FIVE) == 0)
    assert(OddTricks.FIVE.compareTo(OddTricks.FOUR) > 0)
    assert(OddTricks.FIVE.compareTo(OddTricks.SIX) < 0)
  }
  "An OddTricks" should "have getters for java interoperability" in {
    assertResult("Three")(OddTricks.THREE.getName)
    assertResult("3")(OddTricks.THREE.getSymbol)
    assertResult(3)(OddTricks.THREE.getLevel)
  }
  "An OddTricks" should "be constructable from level" in {
    assertResult(Success(OddTricks.THREE))(OddTricks.fromLevel(3))
  }
  "An OddTricks" should "return the correct exception when fromLevel fails" in {
    val myIllegalLevel = 0
    val exception: OddTricksException =
      OddTricks.fromLevel(myIllegalLevel).failed.get.asInstanceOf[OddTricksException]
    assertResult(myIllegalLevel.toString)(exception.illegalLevel)
  }
}
