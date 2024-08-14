package scalabridge

import org.scalatest.flatspec.AnyFlatSpec
import org.junit.jupiter.api.Test

@Test
class DirectionTest extends AnyFlatSpec {
    val north = Direction.NORTH
    val east = Direction.EAST
    val south = Direction.SOUTH
    val west = Direction.WEST
    "A Direction" should "know its direction" in {
        assert(north.isNorth)
        assert(east.isEast)
        assert(south.isSouth)
        assert(west.isWest)
        assert(!north.isWest)
    }
    "A Direction" should "know its side" in {
        assert(north.isNorthSouth);
        assert(south.isNorthSouth);
        assert(east.isEastWest);
        assert(west.isEastWest);
        assert(!north.isEastWest);
        assert(!south.isEastWest);
        assert(!east.isNorthSouth);
        assert(!west.isNorthSouth);
    }
    "A Direction" should "know its immediate next" in {
        assert(north.next == east);
        assert(east.next == south);
        assert(south.next == west);
        assert(west.next == north);
    }
    "A Direction" should "know its non immediate next" in {
        assert(north.next(1) == east);
        assert(north.next(2) == south);
        assert(north.next(3) == west);
        assert(north.next(4) == north);
    }
    "A Direction" should "be acessible from its abbreviation" in {
        assert(Direction.getFromAbbreviation('n') == north);
        assert(Direction.getFromAbbreviation('E') == east);
        assert(Direction.getFromAbbreviation('S') == south);
        assert(Direction.getFromAbbreviation('W') == west);
    }
    "Direction::differenceBetween" should "return zero when given the same direction" in {
        val leader = Direction.WEST;
        assert(0 == Direction.differenceBetween(leader, leader));
    }
    "Direction::differenceBetween" should "return the distance between directions" in {
        val leader = Direction.WEST;
        val direction = Direction.SOUTH;
        val direction2 = Direction.NORTH;

        assert(3 == Direction.differenceBetween(leader, direction));
        assert(1 == Direction.differenceBetween(leader, direction2));
    }
}
