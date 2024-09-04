package scalabridge

import org.scalatest.flatspec.AnyFlatSpec
import org.junit.jupiter.api.Test

@Test
class CardTest extends AnyFlatSpec {
    val twoOfDiamonds = Card(Suit.DIAMONDS, Rank.TWO)
    val threeOfClubs = Card(Suit.CLUBS, Rank.THREE)
    val queenOfHearts = Card(Suit.HEARTS, Rank.QUEEN)
    "A Card" should "be rank comparable" in {
        assert(twoOfDiamonds.compareRank(twoOfDiamonds) == 0)
        assert(twoOfDiamonds.compareRank(threeOfClubs) < 0)
        assert(threeOfClubs.compareRank(twoOfDiamonds) > 0)
    }
    "A Card" should "be suit comparable" in {
        assert(twoOfDiamonds.compareSuit(twoOfDiamonds) == 0)
        assert(twoOfDiamonds.compareSuit(threeOfClubs) > 0)
        assert(threeOfClubs.compareSuit(twoOfDiamonds) < 0)
    }
    "A Card" should "be serializable toString" in {
        assert("d2".equals(twoOfDiamonds.toString))
        assert("c3".equals(threeOfClubs.toString))
    }
    "A Card" should "provide its own points" in {
        assert(twoOfDiamonds.getPoints == 0)
        assert(queenOfHearts.getPoints == 2)
    }
}
