package scalabridge

import scalabridge.nonpure.ContractFromTextValidatedBuilder
import org.junit.jupiter.api.Test

@Test
class ContractTest extends UnitFunSpec {
  private def getContract(text: String) =
    ContractFromTextValidatedBuilder.build(text)
  describe("A Contract") {
    it("should be constructable from text") {
      val fourSpades =
        Contract(OddTricks.FOUR, Strain.SPADES, PenaltyStatus.NONE)
      val fourSpadesDoubled =
        Contract(
          OddTricks.FOUR,
          Strain.SPADES,
          PenaltyStatus.DOUBLED
        )
      val fourSpadesRedoubled =
        Contract(
          OddTricks.FOUR,
          Strain.SPADES,
          PenaltyStatus.REDOUBLED
        )
      getContract("4S") shouldBe fourSpades
      getContract("4SX") shouldBe fourSpadesDoubled
      getContract("4SXX") shouldBe fourSpadesRedoubled
    }
    it("should have an ALLPASS option") {
      val subject = AllPassContract
      subject.isAllPass shouldBe true
      subject.toString() shouldBe "ALLPASS"
    }
  }
}
