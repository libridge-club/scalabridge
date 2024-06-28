package club.libridge.libridgebackend.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HandScalaJavaTest {

    @Test
    public void shouldBeConstructedWithPbnString() {
        HandScala handScala = new HandScala("86.KT2.K85.Q9742");
        assertEquals(13, handScala.size());
    }

}
