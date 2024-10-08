package scalabridge

import scalabridge.exceptions.StrainException
import scala.collection.immutable.ListMap
import scala.util.Try
import scala.util.Success
import scala.util.Failure

/** 
  * The Laws of Bridge use "Denomination" but we will use Strain as it is more
  * common.
  * LAW 18 - BIDS specify the order of the Strains. We use it here.
  */
enum Strain(name: String) extends java.lang.Enum[Strain] {
  case CLUBS extends Strain("Clubs")
  case DIAMONDS extends Strain("Diamonds")
  case HEARTS extends Strain("Hearts")
  case SPADES extends Strain("Spades")
  case NOTRUMPS extends Strain("No Trumps")
  def getName: String = this.name
  def getSymbol: String = String.valueOf(this.name.charAt(0))
}
object Strain {
  def getCLUBS() = CLUBS
  def getDIAMONDS() = DIAMONDS
  def getHEARTS() = HEARTS
  def getSPADES() = SPADES
  def getNOTRUMPS() = NOTRUMPS
  def fromSuit(suit: Suit): Strain =
    suit match
      case scalabridge.Suit.CLUBS    => Strain.CLUBS
      case scalabridge.Suit.DIAMONDS => Strain.DIAMONDS
      case scalabridge.Suit.HEARTS   => Strain.HEARTS
      case scalabridge.Suit.SPADES   => Strain.SPADES

  // Guessing a ListMap is faster than a HashMap here. If this becomes an issue:
  // Benchmark it, write the results here and refactor.
  // Static copy to avoid many copies
  private val nameToStrainMap = ListMap.empty ++ Strain.values
    .map(strain => strain.getSymbol.substring(0, 1) -> strain)
    .toMap

  def fromName(name: String): Try[Strain] = {
    nameToStrainMap.get(name.toUpperCase()) match
      case Some(value) => Success(value)
      case None        => Failure(StrainException(name))
  }
}
