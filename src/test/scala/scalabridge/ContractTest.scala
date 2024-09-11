package scalabridge

import org.scalatest.flatspec.AnyFlatSpec
import org.junit.jupiter.api.Test

@Test
class ContractTest extends AnyFlatSpec {
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
    assertResult(fourSpadesNonVul)(Contract.fromText("4S", VulnerabilityStatus.NONVULNERABLE))
    assertResult(fourSpadesDoubledNonVul)(
      Contract.fromText("4SX", VulnerabilityStatus.NONVULNERABLE)
    )
    assertResult(fourSpadesRedoubledNonVul)(
      Contract.fromText("4SXX", VulnerabilityStatus.NONVULNERABLE)
    )
    assertResult(fourSpadesVul)(Contract.fromText("4S", VulnerabilityStatus.VULNERABLE))
    assertResult(fourSpadesDoubledVul)(Contract.fromText("4SX", VulnerabilityStatus.VULNERABLE))
    assertResult(fourSpadesRedoubledVul)(Contract.fromText("4SXX", VulnerabilityStatus.VULNERABLE))
  }
}
