package scalabridge

import org.junit.jupiter.api.Test

@Test
class CardTest extends UnitFunSpec {
  val twoOfDiamonds = Card(Suit.DIAMONDS, Rank.TWO)
  val threeOfClubs = Card(Suit.CLUBS, Rank.THREE)
  val queenOfHearts = Card(Suit.HEARTS, Rank.QUEEN)
  describe("A Card") {
    it("should be rank comparable") {
      twoOfDiamonds.compareRank(twoOfDiamonds) shouldBe 0
      twoOfDiamonds.compareRank(threeOfClubs) should be < 0
      threeOfClubs.compareRank(twoOfDiamonds) should be > 0
    }
    it("should be suit comparable") {
      twoOfDiamonds.compareSuit(twoOfDiamonds) shouldBe 0
      twoOfDiamonds.compareSuit(threeOfClubs) should be > 0
      threeOfClubs.compareSuit(twoOfDiamonds) should be < 0
    }
    it("should be serializable toString") {
      twoOfDiamonds.toString shouldBe "d2"
      threeOfClubs.toString shouldBe "c3"
    }
    it("should provide its own points") {
      twoOfDiamonds.getPoints shouldBe 0
      queenOfHearts.getPoints shouldBe 2
    }
    it("should have getters for suit and rank for java interoperability") {
      twoOfDiamonds.getSuit shouldBe Suit.DIAMONDS
      twoOfDiamonds.getRank shouldBe Rank.TWO
    }
  }
}
