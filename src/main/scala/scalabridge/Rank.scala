package scalabridge

import scalabridge.exceptions.RankException

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
}
object Rank {
  // Static copy to avoid many copies // TODO refactor into a map or treemap
  private val vals = List(
    Rank.TWO,
    Rank.THREE,
    Rank.FOUR,
    Rank.FIVE,
    Rank.SIX,
    Rank.SEVEN,
    Rank.EIGHT,
    Rank.NINE,
    Rank.TEN,
    Rank.JACK,
    Rank.QUEEN,
    Rank.KING,
    Rank.ACE
  )

  def getFromAbbreviation(abbreviation: Char): Rank = {
    val lowercase = Character.toLowerCase(abbreviation)
    try {
      vals.find(rank => rank.getSymbol.toLowerCase.charAt(0) == lowercase).get
    } catch {
      case e: NoSuchElementException => throw RankException()
    }
  }

}
