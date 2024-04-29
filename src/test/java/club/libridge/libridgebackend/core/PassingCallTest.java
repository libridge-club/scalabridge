package club.libridge.libridgebackend.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PassingCallTest {

    static final PassingCall subject = new PassingCall();

    @Test
    void passingCallIsACall() {
        assertTrue(subject instanceof Call);
    }

    @Test
    void allPassingCallsAreCreatedEqual() {
        PassingCall first = new PassingCall();
        PassingCall second = new PassingCall();
        assertEquals(first,second);
        assertEquals(first.hashCode(),second.hashCode());
    }

    @Test
    void passingCallIsNotTheSameAsAPunitiveCall() {
        PunitiveCall punitiveCall = new PunitiveCall("X");
        assertNotEquals(subject, punitiveCall);
        assertNotEquals(subject.hashCode(),punitiveCall.hashCode());
    }

}
