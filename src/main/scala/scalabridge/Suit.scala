package scalabridge

import scalabridge.exceptions.SuitException
import scala.collection.immutable.ListMap
import scala.util.Try
import scala.util.Success
import scala.util.Failure

/**
  * LAW 1 - THE PACK specify that the suits are ordered.
  * We follow that order here.
  */
enum Suit(name: String, symbol: String, unicodeSymbol: Char) extends java.lang.Enum[Suit] {
  case CLUBS extends Suit("Clubs", "c", '\u2663')
  case DIAMONDS extends Suit("Diamonds", "d", '\u2666')
  case HEARTS extends Suit("Hearts", "h", '\u2665')
  case SPADES extends Suit("Spades", "s", '\u2660')
  def getName: String = this.name
  def getSymbol: String = this.symbol
  def getUnicodeSymbol: Char = this.unicodeSymbol
  override def toString: String = this.symbol
}
object Suit {
  def getCLUBS(): Suit = CLUBS
  def getDIAMONDS(): Suit = DIAMONDS
  def getHEARTS(): Suit = HEARTS
  def getSPADES(): Suit = SPADES
  // Guessing a ListMap is faster than a HashMap here. If this becomes an issue:
  // Benchmark it, write the results here and refactor.
  // Static copy to avoid many copies
  private val symbolToSuitMap = ListMap.empty ++ Suit.values
    .map(suit => suit.getSymbol -> suit)
    .toMap

  def getFromAbbreviation(abbreviation: Char): Try[Suit] = {
    val lowercase = Character.toLowerCase(abbreviation).toString()
    symbolToSuitMap.get(lowercase) match
      case Some(value) => Success(value)
      case None        => Failure(SuitException(abbreviation))
  }

}
