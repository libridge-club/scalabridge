package scalabridge

import scalabridge.exceptions.DirectionException
import scala.jdk.CollectionConverters.*

import scala.util.Failure
import scala.util.Success
import scala.util.Try

enum Direction(completeName: String, abbreviation: Char) extends java.lang.Enum[Direction] {
  case NORTH extends Direction("North", 'N')
  case EAST extends Direction("East", 'E')
  case SOUTH extends Direction("South", 'S')
  case WEST extends Direction("West", 'W')
  def getCompleteName: String = this.completeName
  def getAbbreviation: Char = this.abbreviation
  def isNorth: Boolean = NORTH == this
  def isEast: Boolean = EAST == this
  def isSouth: Boolean = SOUTH == this
  def isWest: Boolean = WEST == this
  def isNorthSouth: Boolean = this.isNorth || this.isSouth
  def isEastWest: Boolean = this.isEast || this.isWest
  def next(n: Int): Direction = Direction.next(this, n)
  def next: Direction = this.next(1)
}
object Direction {
  def getNorth(): Direction = NORTH
  def getEast(): Direction = EAST
  def getSouth(): Direction = SOUTH
  def getWest(): Direction = WEST
  private val length = values.size
  def next(direction: Direction, n: Int): Direction = {
    Direction.fromOrdinal((direction.ordinal + n) % Direction.length)
  }
  def differenceBetween(directionA: Direction, directionB: Direction): Int = {
    val result = (directionB.ordinal - directionA.ordinal) % Direction.length
    if (result < 0) return Direction.length + result
    result
  }

  def getFromAbbreviation(abbreviation: Char): Try[Direction] = {
    abbreviation.toUpper match
      case 'N' => Success(NORTH)
      case 'E' => Success(EAST)
      case 'S' => Success(SOUTH)
      case 'W' => Success(WEST)
      case _   => Failure(DirectionException(abbreviation))
  }

}
