package scalabridge

import scalabridge.exceptions.DirectionException

enum Direction(completeName: String, abbreviation: Char) extends java.lang.Enum[Direction] {
  case NORTH extends Direction("North", 'N')
  case EAST extends Direction("East", 'E')
  case SOUTH extends Direction("South", 'S')
  case WEST extends Direction("West", 'W')
  def getCompleteName: String = this.completeName
  def getAbbreviation: Char = this.abbreviation
  def isNorth: Boolean = Direction.NORTH == this
  def isEast: Boolean = Direction.EAST == this
  def isSouth: Boolean = Direction.SOUTH == this
  def isWest: Boolean = Direction.WEST == this
  def isNorthSouth: Boolean = this.isNorth || this.isSouth
  def isEastWest: Boolean = this.isEast || this.isWest
  def next(n: Int): Direction = 
    Direction.values((this.ordinal + n) % Direction.length)
  def next: Direction = this.next(1)
}
object Direction {
  private val length = Direction.values.length

  def differenceBetween(leader: Direction, direction: Direction): Int = {
    val result = (direction.ordinal - leader.ordinal) % Direction.length
    if (result < 0) return Direction.length + result
    result
  }

  def getFromAbbreviation(abbreviation: Char): Direction = {
    try {
      values.find(direction => direction.getAbbreviation == abbreviation.toUpper).get
    } catch {
      case e: NoSuchElementException => throw DirectionException()
    }
  }

}
