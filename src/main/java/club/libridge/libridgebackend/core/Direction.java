package club.libridge.libridgebackend.core;

import club.libridge.libridgebackend.core.exceptions.DirectionDoesNotExist;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Direction {

    // Clockwise order
    NORTH("North", 'N'), EAST("East", 'E'), SOUTH("South", 'S'), WEST("West", 'W');

    @Getter
    private final String completeName;
    @Getter
    private final char abbreviation;

    public boolean isNorth() {
        return Direction.NORTH == this;
    }

    public boolean isEast() {
        return Direction.EAST == this;
    }

    public boolean isSouth() {
        return Direction.SOUTH == this;
    }

    public boolean isWest() {
        return Direction.WEST == this;
    }

    public boolean isNorthSouth() {
        return isNorth() || isSouth();
    }

    public boolean isEastWest() {
        return isEast() || isWest();
    }

    // Static copy to avoid many copies
    private static Direction[] vals = values();

    public Direction next(int n) {
        return vals[(this.ordinal() + n) % vals.length];
    }

    public Direction next() {
        return this.next(1);
    }

    public Direction getLeaderWhenDealer() {
        return this.next(2);
    }

    public Direction getStrainChooserWhenDealer() {
        return this.next(3);
    }

    public static int differenceBetween(Direction leader, Direction direction) {
        int result = (direction.ordinal() - leader.ordinal()) % vals.length;
        if (result < 0) {
            return vals.length + result;
        }
        return result;
    }

    public static Direction getFromAbbreviation(char abbreviation) {
        for (Direction direction : vals) {
            if (direction.getAbbreviation() == abbreviation) {
                return direction;
            }
        }
        throw new DirectionDoesNotExist();
    }
}
