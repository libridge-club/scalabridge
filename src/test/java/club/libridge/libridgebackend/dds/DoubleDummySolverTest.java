package club.libridge.libridgebackend.dds;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import club.libridge.libridgedds.jna.DDSWrapper;

public class DoubleDummySolverTest {

    private static DDSWrapper ddsWrapper;
    private DoubleDummySolver subject;
    /**
     * 2024 USBC (United States Bridge Championships)
     * Open/Quarterfinal
     * Segment 2
     * Board 21
     * Played on 2024.05.03
     *
     * DDS returns this: [9, 3, 9, 3, 4, 7, 4, 7, 6, 6, 5, 7, 5, 8, 5, 7, 4 7 3 8]
     *
     * Which represents this Double Dummy Table:
     *
     *           N E S W
     * SPADES    9 3 9 3
     * HEARTS    4 7 4 7
     * DIAMONDS  6 6 5 7
     * CLUBS     5 8 5 7
     * NOTRUMPS  4 7 3 8
     *
     */
    private String usbcHandPBNString = "N:q5.kt85.qjt8632. 8.aqj96.ak9.jt93 kt97643.4..ak652 aj2.732.754.q874";
    private List<Integer> usbcHandDDSResponse = List.of(9, 3, 9, 3, 4, 7, 4, 7, 6, 6, 5, 7, 5, 8, 5, 7, 4, 7, 3, 8);

    @BeforeAll
    public static void createWrapper(){ /* Avoid recreating the wrapper many times */
        ddsWrapper = new DDSWrapper();
    }

    @BeforeEach
    public void setup(){
        this.subject = new DoubleDummySolver(DoubleDummySolverTest.ddsWrapper);
    }

    @Test
    public void calcDDtablePBN_shouldFindAndRunLibridgeDDSAndGetTheRightResult(){
        assertEquals(usbcHandDDSResponse, this.subject.calcDDtablePBN(usbcHandPBNString));
    }

    @Test
    public void calculateDoubleDummyTable_String_shouldWrapTheResultInADummyTable(){
     // TODO   
    }

    @Test
    public void calculateDoubleDummyTable_Board_shouldUsePBNUtilsToGetPBNString(){
     // TODO   
    }

    

}
