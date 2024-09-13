package scalabridge

import scala.collection.immutable.ListMap

case class Contract(
    oddTricks: OddTricks,
    strain: Strain,
    penaltyStatus: PenaltyStatus,
    vulnerability: VulnerabilityStatus
) {
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
