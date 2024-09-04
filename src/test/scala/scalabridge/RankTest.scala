package scalabridge

import org.scalatest.flatspec.AnyFlatSpec
import org.junit.jupiter.api.Test
import scalabridge.exceptions.RankException

@Test
class RankTest extends AnyFlatSpec {
    val three = Rank.THREE
    val queen = Rank.QUEEN
    "A Rank" should "be serializable toString" in {
        assert("3".equals(three.toString))
        assert("Q".equals(queen.toString))
    }
    "A Rank" should "get from abbreviation" in {
        assert(Rank.getFromAbbreviation('3') == three)
        assert(Rank.getFromAbbreviation('Q') == queen)
        assert(Rank.getFromAbbreviation('q') == queen)
        assertThrows[RankException] {
            Rank.getFromAbbreviation('x')
        }
    }
    "A Rank" should "have order" in {
        assert(three.compareTo(three) == 0)
        assert(three.compareTo(queen) < 0)
        assert(queen.compareTo(three) > 0)
    }
}
