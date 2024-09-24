package scalabridge

import org.junit.jupiter.api.Test

@Test
class DefaultHandOrderingTest extends UnitFunSpec {
  val subject = DefaultHandOrdering
  val fourOfClubs = Card(Suit.CLUBS, Rank.FOUR)
  val tenOfClubs = Card(Suit.CLUBS, Rank.TEN)
  val fourOfHearts = Card(Suit.HEARTS, Rank.FOUR)
  val tenOfHearts = Card(Suit.HEARTS, Rank.TEN)
  val fourOfDiamonds = Card(Suit.DIAMONDS, Rank.FOUR)
  describe("A CardInsideHandOrdering") {
    it("should return 0 for any comparation to itself") {
      subject.compare(fourOfClubs, fourOfClubs) shouldBe 0
    }
    it("should put higher ranked ranks to the left") {
      subject.compare(tenOfClubs, fourOfClubs) should be < 0
      subject.compare(fourOfClubs, tenOfClubs) should be > 0

      subject.compare(fourOfHearts, tenOfHearts) should be > 0
      subject.compare(tenOfHearts, fourOfHearts) should be < 0
    }
    it("should put higher ranked suits to the left, but with diamonds and clubs switched") {
      subject.compare(fourOfHearts, fourOfClubs) should be < 0
      subject.compare(fourOfClubs, fourOfDiamonds) should be < 0
    }
  }
}
