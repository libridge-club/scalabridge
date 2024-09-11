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
object Contract {

  /**
     * @param text should be in the format LS[P] where
     * L = Odd Tricks level (a digit from 1 to 7)
     * S = strain ( [c,d,h,s,n] )
     * P = Penalty ( "X" for double and "XX" for redouble )
     */
  def fromText(text: String, vulnerabilityStatus: VulnerabilityStatus) = {
    val level: Int = text.substring(0, 1).toInt
    val oddTricks = OddTricks.fromLevel(level)

    val strainText: String = text.substring(1, 2).toUpperCase
    val strain: Strain = strainFromSymbol(strainText).get

    val penaltyText = text.length() match
      case 0 | 1 | 2 => ""
      case _         => text.substring(2)

    val penaltyStatus = penaltyText match
      case "X"  => PenaltyStatus.DOUBLED
      case "XX" => PenaltyStatus.REDOUBLED
      case _    => PenaltyStatus.NONE

    Contract(oddTricks, strain, penaltyStatus, vulnerabilityStatus)
  }

  private def strainFromSymbol(symbol: String): Option[Strain] =
    symbol match
      case "C" => Some(Strain.CLUBS)
      case "D" => Some(Strain.DIAMONDS)
      case "H" => Some(Strain.HEARTS)
      case "S" => Some(Strain.SPADES)
      case "N" => Some(Strain.NOTRUMPS)
      case _   => None

}
