package scalabridge

sealed abstract class Call { // Not a trait because it will be called from Java code
  def isPass: Boolean
  def isDouble: Boolean
  def isRedouble: Boolean
  def isBid: Boolean
}
object PassingCall extends Call {
  def instance: Call = this
  def isPass: Boolean = true
  def isDouble: Boolean = false
  def isRedouble: Boolean = false
  def isBid: Boolean = false
}
sealed abstract class PunitiveCall extends Call {
  def isPass: Boolean = false
  def isBid: Boolean = false
}
object DoubleCall extends PunitiveCall {
  def instance: PunitiveCall = this
  def isDouble: Boolean = true
  def isRedouble: Boolean = false
}
object RedoubleCall extends PunitiveCall {
  def instance: PunitiveCall = this
  def isDouble: Boolean = false
  def isRedouble: Boolean = true
}
case class Bid(oddTricks: OddTricks, strain: Strain) extends Call {
  def isPass: Boolean = false
  def isDouble: Boolean = false
  def isRedouble: Boolean = false
  def isBid: Boolean = true

  /** From the Laws of Bridge 2017: A bid supersedes a previous bid if it
    * designates either the same number of odd tricks in a higher-ranking
    * denomination or a greater number of odd tricks in any denomination.
    */
  def compareTo(other: Bid): Int = {
    val oddTricksComparation = this.oddTricks.compareTo(other.oddTricks)
    if (oddTricksComparation != 0) oddTricksComparation
    else this.strain.compareTo(other.strain)
  }
  override def toString: String = oddTricks.getSymbol + strain.getSymbol
  def getOddTricks: OddTricks = this.oddTricks
  def getStrain: Strain = this.strain
}
