package scalabridge

/**
 * This is governed by LAW 77 - DUPLICATE BRIDGE SCORING TABLE
 */
object ScoreCalculator {

  private def NO_TRUMP_FIRST_TRICK_BONUS = 10

  def score(contract: Contract, tricksMade: TricksMade): Int = {
    val overOrUnderTricks = tricksMade.tricks - 6 - contract.getLevel;
    if (overOrUnderTricks >= 0) contractMadeScore(contract, overOrUnderTricks)
    else -contractFailedScore(contract, -overOrUnderTricks)
  }

  private def contractMadeScore(contract: Contract, overtricks: Int): Int = {
    val penaltyMultiplier = getPenaltyMultiplier(contract);
    val trickScore = getTrickScore(contract, penaltyMultiplier) +
      getNoTrumpFirstTrickBonus(contract, penaltyMultiplier)
    val isGame = trickScore >= 100
    trickScore + getPremiumScore(contract, isGame) + getOvertrickBonus(contract, overtricks);
  }

  private def getPenaltyMultiplier(contract: Contract): Int =
    contract.penaltyStatus match
      case PenaltyStatus.NONE      => 1
      case PenaltyStatus.DOUBLED   => 2
      case PenaltyStatus.REDOUBLED => 4

  private def getTrickScore(contract: Contract, penaltyMultiplier: Int): Int =
    contract.getLevel * penaltyMultiplier * getTrickScoreUndoubled(contract.strain);

  private def getNoTrumpFirstTrickBonus(contract: Contract, penaltyMultiplier: Int): Int =
    contract.strain match
      case Strain.NOTRUMPS => NO_TRUMP_FIRST_TRICK_BONUS * penaltyMultiplier
      case _               => 0

  private def getTrickScoreUndoubled(strain: Strain): Int =
    strain match
      case Strain.CLUBS    => 20
      case Strain.DIAMONDS => 20
      case Strain.HEARTS   => 30
      case Strain.SPADES   => 30
      case Strain.NOTRUMPS => 30

  private def getPremiumScore(contract: Contract, isGame: Boolean): Int = {
    getPremiumScoreGrandSlam(contract) +
      getPremiumScoreSmallSlam(contract) +
      getPremiumScoreGameOrPartscore(contract, isGame) +
      getPremiumScoreDoubledOrRedoubled(contract)
  }
  private def getPremiumScoreGrandSlam(contract: Contract): Int =
    (contract.getLevel, contract.vulnerability) match
      case (7, VulnerabilityStatus.NONVULNERABLE) => 1000
      case (7, VulnerabilityStatus.VULNERABLE)    => 1500
      case _                                      => 0

  private def getPremiumScoreSmallSlam(contract: Contract): Int =
    (contract.getLevel, contract.vulnerability) match
      case (6, VulnerabilityStatus.NONVULNERABLE) => 500
      case (6, VulnerabilityStatus.VULNERABLE)    => 750
      case _                                      => 0

  private def getPremiumScoreGameOrPartscore(contract: Contract, isGame: Boolean): Int =
    (isGame, contract.vulnerability) match
      case (true, VulnerabilityStatus.NONVULNERABLE) => 300
      case (true, VulnerabilityStatus.VULNERABLE)    => 500
      case _                                         => 50

  private def getPremiumScoreDoubledOrRedoubled(contract: Contract): Int =
    contract.penaltyStatus match
      case PenaltyStatus.NONE      => 0
      case PenaltyStatus.DOUBLED   => 50
      case PenaltyStatus.REDOUBLED => 100

  private def getOvertrickBonus(contract: Contract, overtricks: Int): Int = {
    val basevalue = (contract.penaltyStatus, contract.vulnerability) match
      case (PenaltyStatus.NONE, _) => getTrickScoreUndoubled(contract.strain)
      case (PenaltyStatus.DOUBLED, VulnerabilityStatus.NONVULNERABLE)   => 100
      case (PenaltyStatus.DOUBLED, VulnerabilityStatus.VULNERABLE)      => 200
      case (PenaltyStatus.REDOUBLED, VulnerabilityStatus.NONVULNERABLE) => 200
      case (PenaltyStatus.REDOUBLED, VulnerabilityStatus.VULNERABLE)    => 400
    overtricks * basevalue
  }

  private def contractFailedScore(contract: Contract, undertricks: Int): Int = {
    if (undertricks < 0) return 0
    (contract.vulnerability, contract.penaltyStatus) match
      case (VulnerabilityStatus.NONVULNERABLE, PenaltyStatus.NONE) => undertricks * 50;
      case (VulnerabilityStatus.NONVULNERABLE, PenaltyStatus.DOUBLED) =>
        undertricks match {
          case x if x < 4 => (undertricks * 200) - 100;
          case _          => (undertricks * 300) - 400;
        }
      case (VulnerabilityStatus.NONVULNERABLE, PenaltyStatus.REDOUBLED) =>
        undertricks match {
          case x if x < 4 => (undertricks * 400) - 200;
          case _          => (undertricks * 600) - 800;
        }
      case (VulnerabilityStatus.VULNERABLE, PenaltyStatus.NONE)      => undertricks * 100;
      case (VulnerabilityStatus.VULNERABLE, PenaltyStatus.DOUBLED)   => (undertricks * 300) - 100
      case (VulnerabilityStatus.VULNERABLE, PenaltyStatus.REDOUBLED) => (undertricks * 600) - 200;

  }
}
