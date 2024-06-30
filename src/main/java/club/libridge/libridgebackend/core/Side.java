package club.libridge.libridgebackend.core;

public enum Side {

    NORTHSOUTH, EASTWEST;

    public static Side getFromDirection(Direction direction) {
        if (Direction.NORTH == direction || Direction.SOUTH == direction) {
            return NORTHSOUTH;
        } else {
            return EASTWEST;
        }
    }

}
