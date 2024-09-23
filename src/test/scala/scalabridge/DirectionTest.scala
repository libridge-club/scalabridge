package scalabridge

import org.junit.jupiter.api.Test
import scala.util.Success
import scala.util.Failure
import scalabridge.exceptions.DirectionException

@Test
class DirectionTest extends UnitFlatSpec {
  val north = Direction.NORTH
  val east = Direction.EAST
  val south = Direction.SOUTH
  val west = Direction.WEST
  "A Direction" should "have a getCompleteName" in {
    assertResult("North")(north.getCompleteName)
  }
  "A Direction" should "have a getAbbreviation" in {
    assertResult('N')(north.getAbbreviation)
  }
  "A Direction" should "know its immediate next" in {
    assertResult(east)(north.next)
    assertResult(south)(east.next)
    assertResult(west)(south.next)
    assertResult(north)(west.next)
  }
  "A Direction" should "know its non immediate next" in {
    assertResult(east)(north.next(1))
    assertResult(south)(north.next(2))
    assertResult(west)(north.next(3))
    assertResult(north)(north.next(4))
  }
  "A Direction" should "be acessible from its abbreviation as a Try" in {
    assertResult(Success(north))(Direction.getFromAbbreviation('n'))
    assertResult(Success(north))(Direction.getFromAbbreviation('N'))
    assertResult(Success(east))(Direction.getFromAbbreviation('E'))
    assertResult(Success(south))(Direction.getFromAbbreviation('S'))
    assertResult(Success(west))(Direction.getFromAbbreviation('W'))
  }
  "A Direction" should "return the correct exception when getFromAbbreviation fails" in {
    val myIllegalDirection = 'x'
    val exception: DirectionException =
      Direction.getFromAbbreviation(myIllegalDirection).failed.get.asInstanceOf[DirectionException]
    assertResult(myIllegalDirection)(exception.illegalDirection)
  }
  "Direction::differenceBetween" should "return zero when given the same direction" in {
    val leader = Direction.WEST
    assertResult(0)(Direction.differenceBetween(leader, leader))
  }
  "Direction::differenceBetween" should "return the distance between directions" in {
    val leader = Direction.WEST
    val direction = Direction.SOUTH
    val direction2 = Direction.NORTH

    assertResult(3)(Direction.differenceBetween(leader, direction))
    assertResult(1)(Direction.differenceBetween(leader, direction2))
  }
  "A Direction" should "have getters for java interoperability" in {
    assertResult(Direction.NORTH)(Direction.getNorth())
    assertResult(Direction.EAST)(Direction.getEast())
    assertResult(Direction.SOUTH)(Direction.getSouth())
    assertResult(Direction.WEST)(Direction.getWest())
  }
}
