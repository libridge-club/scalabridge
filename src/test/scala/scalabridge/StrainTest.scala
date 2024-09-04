package scalabridge

import org.scalatest.flatspec.AnyFlatSpec
import org.junit.jupiter.api.Test
import scalabridge.exceptions.StrainException

@Test
class StrainTest extends AnyFlatSpec {
    val clubs = Strain.CLUBS
    val notrumps = Strain.NOTRUMPS
    "A Strain" should "get from suit" in {
        assert(Strain.fromSuit(Suit.CLUBS)==Strain.CLUBS)
    }
    "A Strain" should "get from name" in {
        assert(Strain.fromName("c")==Strain.CLUBS)
        assert(Strain.fromName("C")==Strain.CLUBS)
        assert(Strain.fromName("N")==Strain.NOTRUMPS)
        assertThrows[StrainException] {
            Strain.fromName("x")
        }
    }
    "A Strain" should "have order" in {
        assert(clubs.compareTo(clubs) == 0)
        assert(clubs.compareTo(notrumps) < 0)
        assert(notrumps.compareTo(clubs) > 0)
    }
}
