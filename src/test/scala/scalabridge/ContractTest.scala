package scalabridge

import org.scalatest.flatspec.AnyFlatSpec
import org.junit.jupiter.api.Test
import scalabridge.nonpure.ContractFromTextValidatedBuilder

@Test
class ContractTest extends AnyFlatSpec {
  private def getContract(text: String, vulnerabilityStatus: VulnerabilityStatus) =
    ContractFromTextValidatedBuilder.build(text, vulnerabilityStatus)
  "A Contract" should "be constructable from text" in {
    val fourSpadesNonVul =
      Contract(OddTricks.FOUR, Strain.SPADES, PenaltyStatus.NONE, VulnerabilityStatus.NONVULNERABLE)
    val fourSpadesDoubledNonVul =
      Contract(
        OddTricks.FOUR,
        Strain.SPADES,
        PenaltyStatus.DOUBLED,
        VulnerabilityStatus.NONVULNERABLE
      )
    val fourSpadesRedoubledNonVul =
      Contract(
        OddTricks.FOUR,
        Strain.SPADES,
        PenaltyStatus.REDOUBLED,
        VulnerabilityStatus.NONVULNERABLE
      )
    val fourSpadesVul =
      Contract(OddTricks.FOUR, Strain.SPADES, PenaltyStatus.NONE, VulnerabilityStatus.VULNERABLE)
    val fourSpadesDoubledVul =
      Contract(
        OddTricks.FOUR,
        Strain.SPADES,
        PenaltyStatus.DOUBLED,
        VulnerabilityStatus.VULNERABLE
      )
    val fourSpadesRedoubledVul =
      Contract(
        OddTricks.FOUR,
        Strain.SPADES,
        PenaltyStatus.REDOUBLED,
        VulnerabilityStatus.VULNERABLE
      )
    assertResult(fourSpadesNonVul)(getContract("4S", VulnerabilityStatus.NONVULNERABLE))
    assertResult(fourSpadesDoubledNonVul)(
      getContract("4SX", VulnerabilityStatus.NONVULNERABLE)
    )
    assertResult(fourSpadesRedoubledNonVul)(
      getContract("4SXX", VulnerabilityStatus.NONVULNERABLE)
    )
    assertResult(fourSpadesVul)(getContract("4S", VulnerabilityStatus.VULNERABLE))
    assertResult(fourSpadesDoubledVul)(getContract("4SX", VulnerabilityStatus.VULNERABLE))
    assertResult(fourSpadesRedoubledVul)(getContract("4SXX", VulnerabilityStatus.VULNERABLE))
  }
}
