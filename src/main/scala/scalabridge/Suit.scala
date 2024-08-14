package scalabridge

import scalabridge.exceptions.SuitException

enum Suit(name: String, symbol: String, unicodeSymbol: Char)
    extends java.lang.Enum[Suit] {
  case DIAMONDS extends Suit("Diamonds", "d", '\u2666')
  case CLUBS extends Suit("Clubs", "c", '\u2663')
  case HEARTS extends Suit("Hearts", "h", '\u2665')
  case SPADES extends Suit("Spades", "s", '\u2660')
  def getName: String = this.name
  def getSymbol: String = this.symbol
  def getUnicodeSymbol: Char = this.unicodeSymbol
}
object Suit {
  // Static copy to avoid many copies
  private val vals = List(Suit.DIAMONDS, Suit.CLUBS, Suit.HEARTS, Suit.SPADES)

  // TODO remove try catch and use option
  def getFromAbbreviation(abbreviation: Char): Suit = {
    val lowercase = Character.toLowerCase(abbreviation)
    try {
      vals.find(suit => suit.getSymbol.toLowerCase.charAt(0) == lowercase).get
    } catch {
      case e: NoSuchElementException => throw SuitException()
    }

  }

}
