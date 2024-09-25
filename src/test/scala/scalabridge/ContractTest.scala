package scalabridge

import scalabridge.nonpure.ContractFromTextValidatedBuilder
import org.junit.jupiter.api.Test

@Test
class ContractTest extends UnitFunSpec {
  private def getContract(text: String, vulnerabilityStatus: VulnerabilityStatus) =
    ContractFromTextValidatedBuilder.build(text, vulnerabilityStatus)
  describe("A Contract") {
    it("should be constructable from text") {
      val nonVul = VulnerabilityStatus.NONVULNERABLE
      val vul = VulnerabilityStatus.VULNERABLE
      val fourSpadesNonVul =
        Contract(OddTricks.FOUR, Strain.SPADES, PenaltyStatus.NONE, nonVul)
      val fourSpadesDoubledNonVul =
        Contract(
          OddTricks.FOUR,
          Strain.SPADES,
          PenaltyStatus.DOUBLED,
          nonVul
        )
      val fourSpadesRedoubledNonVul =
        Contract(
          OddTricks.FOUR,
          Strain.SPADES,
          PenaltyStatus.REDOUBLED,
          nonVul
        )
      val fourSpadesVul =
        Contract(OddTricks.FOUR, Strain.SPADES, PenaltyStatus.NONE, vul)
      val fourSpadesDoubledVul =
        Contract(
          OddTricks.FOUR,
          Strain.SPADES,
          PenaltyStatus.DOUBLED,
          vul
        )
      val fourSpadesRedoubledVul =
        Contract(
          OddTricks.FOUR,
          Strain.SPADES,
          PenaltyStatus.REDOUBLED,
          vul
        )

      getContract("4S", nonVul) shouldBe fourSpadesNonVul
      getContract("4SX", nonVul) shouldBe fourSpadesDoubledNonVul
      getContract("4SXX", nonVul) shouldBe fourSpadesRedoubledNonVul
      getContract("4S", vul) shouldBe fourSpadesVul
      getContract("4SX", vul) shouldBe fourSpadesDoubledVul
      getContract("4SXX", vul) shouldBe fourSpadesRedoubledVul
    }
    it("should have an ALLPASS option") {
      val subject = AllPassContract
      subject.isAllPass shouldBe true
      subject.toString() shouldBe "ALLPASS"
    }
  }
}
