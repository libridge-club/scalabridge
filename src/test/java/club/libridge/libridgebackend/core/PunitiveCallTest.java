package club.libridge.libridgebackend.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import org.junit.jupiter.api.Test;

class PunitiveCallTest {

    static final PunitiveCall _double = new PunitiveCall("X");
    static final PunitiveCall redouble = new PunitiveCall("XX");

    @Test
    void punitiveCallIsACall() {
        assertTrue(_double instanceof Call);
    }

    @Test
    void doubleShouldBeAPunitiveCall() {
        assertNotNull(_double);
        assertTrue(_double.isDouble());
    }

    @Test
    void redoubleShouldBeAPunitiveCall() {
        assertNotNull(redouble);
        assertTrue(redouble.isRedouble());
    }

    @Test
    void punitiveCallShouldBeEitherDoubleOrRedoubleButNotBoth() {
        assertTrue(redouble.isDouble() ^ redouble.isRedouble());
        assertTrue(_double.isDouble() ^ _double.isRedouble());
    }

    @Test
    void allDoublesAreCreatedEqual() {
        PunitiveCall other = new PunitiveCall("X");
        assertEquals(_double,other);
        assertEquals(_double.hashCode(),other.hashCode());
        HashSet<Call> mySet = new HashSet<Call>();
        mySet.add(_double);
        mySet.add(other);
        assertEquals(1,mySet.size());
    }

    @Test
    void allRedoublesAreCreatedEqual() {
        PunitiveCall other = new PunitiveCall("XX");
        assertEquals(redouble,other);
        assertEquals(redouble.hashCode(),other.hashCode());
        HashSet<Call> mySet = new HashSet<Call>();
        mySet.add(redouble);
        mySet.add(other);
        assertEquals(1,mySet.size());
    }

    @Test
    void aDoubleIsNotOtherThings() {
        assertNotEquals(_double,redouble);
        assertNotEquals(_double.hashCode(),redouble.hashCode());
        assertNotEquals(_double,null);
        assertNotEquals(_double,new String("a"));
    }

    @Test
    void aRedoubleIsNotOtherThings() {
        assertNotEquals(redouble,_double);
        assertNotEquals(redouble.hashCode(),_double.hashCode());
        assertNotEquals(redouble,null);
        assertNotEquals(redouble,new String("a"));
    }

}
