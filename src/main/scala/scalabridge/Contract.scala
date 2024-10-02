package scalabridge

sealed abstract class Contract {
  val isAllPass: Boolean
}
object Contract {
  def apply(
      oddTricks: OddTricks,
      strain: Strain,
      penaltyStatus: PenaltyStatus
  ) = DefaultContract(oddTricks, strain, penaltyStatus)
}

case class DefaultContract(
    oddTricks: OddTricks,
    strain: Strain,
    penaltyStatus: PenaltyStatus
) extends Contract {

  override val isAllPass: Boolean = false

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
}

case object AllPassContract extends Contract {
  override val isAllPass: Boolean = true
  override def toString(): String = "ALLPASS"
}
