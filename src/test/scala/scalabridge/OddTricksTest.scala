package scalabridge

import org.scalatest.flatspec.AnyFlatSpec
import org.junit.jupiter.api.Test

@Test
class OddTricksTest extends AnyFlatSpec {
    "An OddTricks" should "be comparable" in {
        assert(OddTricks.FIVE.compareTo(OddTricks.FIVE)==0)
        assert(OddTricks.FIVE.compareTo(OddTricks.FOUR)>0)
        assert(OddTricks.FIVE.compareTo(OddTricks.SIX)<0)
    }
    "An OddTricks" should "have getters for java interoperability" in {
        assertResult("Three")(OddTricks.THREE.getName)
        assertResult("3")(OddTricks.THREE.getSymbol)
        assertResult(3)(OddTricks.THREE.getLevel)
    }
    "An OddTricks" should "be constructable from level" in {
        assertResult(OddTricks.THREE)(OddTricks.fromLevel(3))
    }
}
