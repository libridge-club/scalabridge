package scalabridge

import scala.collection.immutable.ListMap

sealed abstract class Contract {
  def isAllPass: Boolean
}
object Contract {
  def apply(
      oddTricks: OddTricks,
      strain: Strain,
      penaltyStatus: PenaltyStatus,
      vulnerability: VulnerabilityStatus
  ) = DefaultContract(oddTricks, strain, penaltyStatus, vulnerability)
}

case class DefaultContract(
    oddTricks: OddTricks,
    strain: Strain,
    penaltyStatus: PenaltyStatus,
    vulnerability: VulnerabilityStatus
) extends Contract {

  override def isAllPass: Boolean = false

  def getLevel = oddTricks.getLevel
  def isDoubled = PenaltyStatus.DOUBLED == penaltyStatus
  def isRedoubled = PenaltyStatus.REDOUBLED == penaltyStatus
  override def toString(): String = {
    val response = s"${this.getLevel.toString()}${this.strain.getSymbol}";
    if (this.isDoubled) s"${response}X";
    else if (this.isRedoubled) s"${response}XX";
    else response;
  }
  def getStrain = this.strain
  def isVulnerable = this.vulnerability.value
}

object AllPassContract extends Contract {
  override def isAllPass: Boolean = true
  override def toString(): String = "ALLPASS"
}
