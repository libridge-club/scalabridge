package scalabridge

import org.scalatest.flatspec.AnyFlatSpec
import org.junit.jupiter.api.Test
import scalabridge.exceptions.SuitException

@Test
class SuitTest extends AnyFlatSpec {
    val clubs = Suit.CLUBS
    val diamonds = Suit.DIAMONDS
    "A Suit" should "be serializable toString" in {
        assert("c".equals(clubs.toString))
        assert("d".equals(diamonds.toString))
    }
    "A Suit" should "get from abbreviation" in {
        assert(Suit.getFromAbbreviation('C') == Suit.CLUBS)
        assert(Suit.getFromAbbreviation('c') == Suit.CLUBS)
        assert(Suit.getFromAbbreviation('h') == Suit.HEARTS)
        assertThrows[SuitException] {
            Suit.getFromAbbreviation('x')
        }
    }
    "A Suit" should "have order" in {
        assert(clubs.compareTo(clubs) == 0)
        assert(clubs.compareTo(diamonds) < 0)
        assert(diamonds.compareTo(clubs) > 0)
    }
}
