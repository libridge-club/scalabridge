package scalabridge

import scalabridge.exceptions.RankException
import scala.collection.immutable.HashMap
import scala.util.Try
import scala.util.Success
import scala.util.Failure

/**
  * LAW 1 - THE PACK specify that the ranks are ordered.
  * We follow that order here.
  */
enum Rank(name: String, symbol: String) extends java.lang.Enum[Rank] {
  case TWO extends Rank("Two", "2")
  case THREE extends Rank("Three", "3")
  case FOUR extends Rank("Four", "4")
  case FIVE extends Rank("Five", "5")
  case SIX extends Rank("Six", "6")
  case SEVEN extends Rank("Seven", "7")
  case EIGHT extends Rank("Eight", "8")
  case NINE extends Rank("Nine", "9")
  case TEN extends Rank("Ten", "T")
  case JACK extends Rank("Jack", "J")
  case QUEEN extends Rank("Queen", "Q")
  case KING extends Rank("King", "K")
  case ACE extends Rank("Ace", "A")
  def getName: String = this.name
  def getSymbol: String = this.symbol
  override def toString: String = this.symbol
}
object Rank {
  def getTWO(): Rank = TWO
  def getTHREE(): Rank = THREE
  def getFOUR(): Rank = FOUR
  def getFIVE(): Rank = FIVE
  def getSIX(): Rank = SIX
  def getSEVEN(): Rank = SEVEN
  def getEIGHT(): Rank = EIGHT
  def getNINE(): Rank = NINE
  def getTEN(): Rank = TEN
  def getJACK(): Rank = JACK
  def getQUEEN(): Rank = QUEEN
  def getKING(): Rank = KING
  def getACE(): Rank = ACE
  // Guessing a HashMap is faster than a ListMap here. If this becomes an issue:
  // Benchmark it, write the results here and refactor.
  // Static value to avoid many copies
  private val symbolToRankMap = HashMap.empty ++ Rank.values
    .map(rank => rank.getSymbol -> rank)
    .toMap

  def getFromAbbreviation(abbreviation: Char): Try[Rank] = {
    val uppercase = Character.toUpperCase(abbreviation).toString()
    symbolToRankMap.get(uppercase) match
      case Some(value) => Success(value)
      case None        => Failure(RankException(abbreviation))
  }

}
