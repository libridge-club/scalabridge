package scalabridge

import org.junit.jupiter.api.Test
import scala.util.Success
import scalabridge.exceptions.OddTricksException

@Test
class OddTricksTest extends UnitFunSpec {
  describe("An OddTricks") {
    it("should be comparable") {
      OddTricks.FIVE.compareTo(OddTricks.FIVE) shouldBe 0
      OddTricks.FIVE.compareTo(OddTricks.FOUR) should be > 0
      OddTricks.FIVE.compareTo(OddTricks.SIX) should be < 0
    }
    it("should have getters for java interoperability") {
      OddTricks.THREE.getName shouldBe "Three"
      OddTricks.THREE.getSymbol shouldBe "3"
      OddTricks.THREE.getLevel shouldBe 3
    }
    it("should be constructable from level") {
      OddTricks.fromLevel(3) shouldBe Success(OddTricks.THREE)
    }
    it("should return the correct exception when fromLevel fails") {
      val myIllegalLevel = 0
      val exception: OddTricksException =
        OddTricks.fromLevel(myIllegalLevel).failed.get.asInstanceOf[OddTricksException]
      exception.illegalLevel shouldBe myIllegalLevel.toString
    }
  }
}
