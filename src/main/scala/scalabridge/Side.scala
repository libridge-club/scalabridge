package scalabridge

enum Side extends java.lang.Enum[Side] {
  case NORTHSOUTH
  case EASTWEST
}
object Side {
  def getFromDirection(direction: Direction): Side = {
    if ((Direction.NORTH == direction) || (Direction.SOUTH == direction))
      NORTHSOUTH
    else EASTWEST
  }
}
