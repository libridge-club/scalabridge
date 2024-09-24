package scalabridge

import org.junit.jupiter.api.Test

@Test
class CallTest extends UnitFunSpec {
  val fourSpadesBid = Bid(OddTricks.FOUR, Strain.SPADES)
  val threeSpadesBid = Bid(OddTricks.THREE, Strain.SPADES)
  val fourNoTrumpsBid = Bid(OddTricks.FOUR, Strain.NOTRUMPS)
  describe("A Bid") {
    it("should be comparable") {
      fourSpadesBid.compareTo(fourSpadesBid) shouldBe 0
      fourSpadesBid.compareTo(threeSpadesBid) should be > 0
      fourSpadesBid.compareTo(fourNoTrumpsBid) should be < 0
    }
    it("should be serializable toString") {
      fourSpadesBid.toString shouldBe "4S"
      fourNoTrumpsBid.toString shouldBe "4N"
      threeSpadesBid.toString shouldBe "3S"
    }
    it("should have getters for java interoperability") {
      fourSpadesBid.getOddTricks shouldBe OddTricks.FOUR
      fourSpadesBid.getStrain shouldBe Strain.SPADES
    }
  }
}
