package scalabridge

/** The Laws of Bridge use "Denomination" but we will use Strain as it is more
  * common.
  */
enum Strain(name: String) extends java.lang.Enum[Strain] {
  case CLUBS extends Strain("Clubs")
  case DIAMONDS extends Strain("Diamonds")
  case HEARTS extends Strain("Hearts")
  case SPADES extends Strain("Spades")
  case NOTRUMPS extends Strain("No Trumps")
  def getSymbol: String = String.valueOf(this.name.charAt(0))
  def getName: String = this.name
}
object Strain {
  val mapFromSuit: Map[Suit, Strain] = Map(
    Suit.SPADES -> Strain.SPADES,
    Suit.HEARTS -> Strain.HEARTS,
    Suit.DIAMONDS -> Strain.DIAMONDS,
    Suit.CLUBS -> Strain.CLUBS
  )
  def fromSuit(suit: Suit): Strain =
    mapFromSuit.getOrElse(suit, Strain.NOTRUMPS)
  def fromName(name: String): Strain = {
    Strain.values.find(strain => strain.getName == name) match
      case Some(value) => value
      case None        => Strain.NOTRUMPS
  }
}
