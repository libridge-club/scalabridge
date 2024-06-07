package club.libridge.libridgebackend.core;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class VerySlowTestIT {

    @Test
    void countTo1Billion() {
        for (long i = 0; i < (1000000000L); i++) {
            assertTrue(true);
        }
    }

}
