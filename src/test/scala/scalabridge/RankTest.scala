package scalabridge

import org.junit.jupiter.api.Test
import scalabridge.exceptions.RankException
import scala.util.Success

@Test
class RankTest extends UnitFunSpec {
  val three = Rank.THREE
  val queen = Rank.QUEEN
  describe("A Rank") {
    it("should be serializable toString") {
      three.toString shouldBe "3"
      queen.toString shouldBe "Q"
    }
    it("should get from abbreviation") {
      Rank.getFromAbbreviation('3') shouldBe Success(three)
      Rank.getFromAbbreviation('Q') shouldBe Success(queen)
      Rank.getFromAbbreviation('q') shouldBe Success(queen)
      assert(Rank.getFromAbbreviation('x').isFailure)
    }
    it("should return the correct exception when getFromAbbreviation fails") {
      val myIllegalRank = 'x'
      val exception: RankException =
        Rank.getFromAbbreviation(myIllegalRank).failed.get.asInstanceOf[RankException]
      exception.illegalRank shouldBe myIllegalRank
    }
    it("should have order") {
      three.compareTo(three) shouldBe 0
      three.compareTo(queen) should be < 0
      queen.compareTo(three) should be > 0
    }
  }
}
