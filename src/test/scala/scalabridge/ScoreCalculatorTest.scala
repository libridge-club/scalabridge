package scalabridge

import org.junit.jupiter.api.Test
import TricksMade._
import scalabridge.nonpure.ContractFromTextValidatedBuilder

@Test
class ScoreCalculatorTest extends UnitFlatSpec {
  private def getContract(text: String, vulnerabilityStatus: VulnerabilityStatus) =
    ContractFromTextValidatedBuilder.build(text, vulnerabilityStatus)

  private val nonVul = VulnerabilityStatus.NONVULNERABLE
  private val vul = VulnerabilityStatus.VULNERABLE
  "A ScoreCalculator" should "score 1N= correctly" in {
    assertResult(90)(ScoreCalculator.score(getContract("1N", nonVul), SEVEN))
    assertResult(90)(ScoreCalculator.score(getContract("1N", vul), SEVEN))
  }
  "A ScoreCalculator" should "score 3N= correctly" in {
    assertResult(400)(ScoreCalculator.score(getContract("3N", nonVul), NINE))
    assertResult(600)(ScoreCalculator.score(getContract("3N", vul), NINE))
  }
  "A ScoreCalculator" should "score 7NXX= correctly" in {
    assertResult(2280)(ScoreCalculator.score(getContract("7NXX", nonVul), THIRTEEN))
    assertResult(2980)(ScoreCalculator.score(getContract("7NXX", vul), THIRTEEN))
  }
  "A ScoreCalculator" should "score some arbitrary contracts correctly" in {
    assertResult(70)(ScoreCalculator.score(getContract("1C", vul), SEVEN));
    assertResult(80)(ScoreCalculator.score(getContract("1H", vul), SEVEN));
    assertResult(90)(ScoreCalculator.score(getContract("1C", vul), EIGHT));
    assertResult(90)(ScoreCalculator.score(getContract("1N", vul), SEVEN));
    assertResult(110)(ScoreCalculator.score(getContract("1H", vul), EIGHT));
    assertResult(120)(ScoreCalculator.score(getContract("1N", vul), EIGHT));
    assertResult(180)(ScoreCalculator.score(getContract("2DX", vul), EIGHT));
    assertResult(180)(ScoreCalculator.score(getContract("1NX", vul), SEVEN));
    assertResult(230)(ScoreCalculator.score(getContract("3S", nonVul), TWELVE));
    assertResult(380)(ScoreCalculator.score(getContract("2DX", vul), NINE));
    assertResult(420)(ScoreCalculator.score(getContract("5D", nonVul), TWELVE));
    assertResult(620)(ScoreCalculator.score(getContract("5D", vul), TWELVE));
    assertResult(670)(ScoreCalculator.score(getContract("3DX", vul), NINE));
    assertResult(750)(ScoreCalculator.score(getContract("3NX", nonVul), ELEVEN));
    assertResult(760)(ScoreCalculator.score(getContract("2DXX", vul), EIGHT));
    assertResult(870)(ScoreCalculator.score(getContract("3DX", vul), TEN));
    assertResult(980)(ScoreCalculator.score(getContract("6H", nonVul), TWELVE));
    assertResult(1160)(ScoreCalculator.score(getContract("2DXX", vul), NINE));
    assertResult(1330)(ScoreCalculator.score(getContract("3HX", vul), TWELVE));
    assertResult(1370)(ScoreCalculator.score(getContract("6C", vul), TWELVE));
    assertResult(1430)(ScoreCalculator.score(getContract("6H", vul), TWELVE));
  }
  "A ScoreCalculator" should "score all failed contracts correctly" in {
    // Trusting completely in http://rpbridge.net/cgi-bin/xsc2.pl
    val values = Seq(
      (1, -50, -100, -200, -100, -200, -400), // -1
      (2, -100, -300, -600, -200, -500, -1000), // -2
      (3, -150, -500, -1000, -300, -800, -1600), // -3
      (4, -200, -800, -1600, -400, -1100, -2200), // -4
      (5, -250, -1100, -2200, -500, -1400, -2800), // -5
      (6, -300, -1400, -2800, -600, -1700, -3400), // -6
      (7, -350, -1700, -3400, -700, -2000, -4000), // -7
      (8, -400, -2000, -4000, -800, -2300, -4600), // -8
      (9, -450, -2300, -4600, -900, -2600, -5200), // -9
      (10, -500, -2600, -5200, -1000, -2900, -5800), // -10
      (11, -550, -2900, -5800, -1100, -3200, -6400), // -11
      (12, -600, -3200, -6400, -1200, -3500, -7000), // -12
      (13, -650, -3500, -7000, -1300, -3800, -7600) // -13
    )
    val sevenOddTricks = OddTricks.SEVEN
    val anyStrain = Strain.NOTRUMPS
    val none = PenaltyStatus.NONE
    val doubled = PenaltyStatus.DOUBLED
    val redoubled = PenaltyStatus.REDOUBLED
    val nonvulnerable = VulnerabilityStatus.NONVULNERABLE
    val vulnerable = VulnerabilityStatus.VULNERABLE

    val nonVul = Contract(sevenOddTricks, anyStrain, none, nonvulnerable);
    val nonVulX = Contract(sevenOddTricks, anyStrain, doubled, nonvulnerable)
    val nonVulXX = Contract(sevenOddTricks, anyStrain, redoubled, nonvulnerable)
    val vul = Contract(sevenOddTricks, anyStrain, none, vulnerable)
    val vulX = Contract(sevenOddTricks, anyStrain, doubled, vulnerable)
    val vulXX = Contract(sevenOddTricks, anyStrain, redoubled, vulnerable)
    values.foreach(downSome =>
      val (i, a, b, c, d, e, f) = downSome
      val tricksMade = TricksMade.fromInt(13 - i + 1)
      assertResult(a)(ScoreCalculator.score(nonVul, tricksMade))
      assertResult(b)(ScoreCalculator.score(nonVulX, tricksMade))
      assertResult(c)(ScoreCalculator.score(nonVulXX, tricksMade))
      assertResult(d)(ScoreCalculator.score(vul, tricksMade))
      assertResult(e)(ScoreCalculator.score(vulX, tricksMade))
      assertResult(f)(ScoreCalculator.score(vulXX, tricksMade))
    )
  }
}
