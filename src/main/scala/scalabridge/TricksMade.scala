package scalabridge

enum TricksMade(val tricks: Int) {
  case ZERO extends TricksMade(0)
  case ONE extends TricksMade(1)
  case TWO extends TricksMade(2)
  case THREE extends TricksMade(3)
  case FOUR extends TricksMade(4)
  case FIVE extends TricksMade(5)
  case SIX extends TricksMade(6)
  case SEVEN extends TricksMade(7)
  case EIGHT extends TricksMade(8)
  case NINE extends TricksMade(9)
  case TEN extends TricksMade(10)
  case ELEVEN extends TricksMade(11)
  case TWELVE extends TricksMade(12)
  case THIRTEEN extends TricksMade(13)
}
object TricksMade {
  def fromInt(tricks: Int) = {
    TricksMade.fromOrdinal(tricks - 1)
  }
}
