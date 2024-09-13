package scalabridge

import scalabridge.exceptions.OddTricksException
import scala.util.Success
import scala.util.Failure
import scala.util.Try

enum OddTricks(name: String, symbol: String, level: Int) extends java.lang.Enum[OddTricks] {
  case ONE extends OddTricks("One", "1", 1)
  case TWO extends OddTricks("Two", "2", 2)
  case THREE extends OddTricks("Three", "3", 3)
  case FOUR extends OddTricks("Four", "4", 4)
  case FIVE extends OddTricks("Five", "5", 5)
  case SIX extends OddTricks("Six", "6", 6)
  case SEVEN extends OddTricks("Seven", "7", 7)
  def getName: String = this.name
  def getSymbol: String = this.symbol
  def getLevel: Int = this.level
}
object OddTricks {
  def getONE() = ONE
  def getTWO() = TWO
  def getTHREE() = THREE
  def getFOUR() = FOUR
  def getFIVE() = FIVE
  def getSIX() = SIX
  def getSEVEN() = SEVEN
  private val mapFromLevel: Map[Int, OddTricks] =
    OddTricks.values.map(oddTrick => oddTrick.getLevel -> oddTrick).toMap

  def fromLevel(level: Int): Try[OddTricks] = {
    OddTricks.mapFromLevel.get(level) match {
      case Some(oddTrick) => Success(oddTrick)
      case None           => Failure(OddTricksException(level.toString))
    }
  }
}
