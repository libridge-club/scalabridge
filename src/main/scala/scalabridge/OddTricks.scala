package scalabridge

enum OddTricks(name: String, symbol: String, level: Int)
    extends java.lang.Enum[OddTricks] {
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
  val mapFromLevel: Map[Int, OddTricks] =
    OddTricks.values.map(oddTrick => oddTrick.getLevel -> oddTrick).toMap

  def fromLevel(level: Int): OddTricks = {
    if (level < 1 || level > 7)
      throw new IllegalArgumentException(
        "Level should be between 1 and 7 inclusive."
      )
    OddTricks.mapFromLevel.get(level) match {
      case Some(oddTrick) => oddTrick
      case None =>
        throw new IllegalArgumentException(
          "Level should be between 1 and 7 inclusive."
        )
    }
  }
}
