package scalabridge

import org.junit.jupiter.api.Test
import scala.util.Success
import scala.util.Failure
import scalabridge.exceptions.DirectionException

@Test
class DirectionTest extends UnitFunSpec {
  val north = Direction.NORTH
  val east = Direction.EAST
  val south = Direction.SOUTH
  val west = Direction.WEST
  describe("A Direction") {
    it("should have a getCompleteName") {
      north.getCompleteName shouldBe "North"
    }
    it("should have a getAbbreviation") {
      north.getAbbreviation shouldBe 'N'
    }
    it("should know its immediate next") {
      north.next shouldBe east
      east.next shouldBe south
      south.next shouldBe west
      west.next shouldBe north
    }
    it("should know its non immediate next") {
      north.next(1) shouldBe east
      north.next(2) shouldBe south
      north.next(3) shouldBe west
      north.next(4) shouldBe north
    }
    it("should be acessible from its abbreviation as a Try") {
      Direction.getFromAbbreviation('n') shouldBe Success(north)
      Direction.getFromAbbreviation('N') shouldBe Success(north)
      Direction.getFromAbbreviation('E') shouldBe Success(east)
      Direction.getFromAbbreviation('S') shouldBe Success(south)
      Direction.getFromAbbreviation('W') shouldBe Success(west)
    }
    it("should return the correct exception when getFromAbbreviation fails") {
      val myIllegalDirection = 'x'
      val exception: DirectionException = Direction
        .getFromAbbreviation(myIllegalDirection)
        .failed
        .get
        .asInstanceOf[DirectionException]
      exception.illegalDirection shouldBe myIllegalDirection
    }
    describe("differenceBetween function") {
      it("should return zero when given the same direction") {
        val leader = Direction.WEST
        Direction.differenceBetween(leader, leader) shouldBe 0
      }
      it("should return the distance between directions") {
        val leader = Direction.WEST
        val direction = Direction.SOUTH
        val direction2 = Direction.NORTH

        Direction.differenceBetween(leader, direction) shouldBe 3
        Direction.differenceBetween(leader, direction2) shouldBe 1
      }
    }
    it("should have getters for java interoperability") {
      Direction.getNorth() shouldBe Direction.NORTH
      Direction.getEast() shouldBe Direction.EAST
      Direction.getSouth() shouldBe Direction.SOUTH
      Direction.getWest() shouldBe Direction.WEST
    }
  }
}
