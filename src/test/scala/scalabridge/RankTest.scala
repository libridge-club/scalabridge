package scalabridge

import org.junit.jupiter.api.Test
import scalabridge.exceptions.RankException
import scala.util.Success

@Test
class RankTest extends UnitFlatSpec {
  val three = Rank.THREE
  val queen = Rank.QUEEN
  "A Rank" should "be serializable toString" in {
    assertResult("3")(three.toString)
    assertResult("Q")(queen.toString)
  }
  "A Rank" should "get from abbreviation" in {
    assertResult(Success(three))(Rank.getFromAbbreviation('3'))
    assertResult(Success(queen))(Rank.getFromAbbreviation('Q'))
    assertResult(Success(queen))(Rank.getFromAbbreviation('q'))
    assert(Rank.getFromAbbreviation('x').isFailure)
  }
  "A Rank" should "return the correct exception when getFromAbbreviation fails" in {
    val myIllegalRank = 'x'
    val exception: RankException =
      Rank.getFromAbbreviation(myIllegalRank).failed.get.asInstanceOf[RankException]
    assertResult(myIllegalRank)(exception.illegalRank)
  }
  "A Rank" should "have order" in {
    assert(three.compareTo(three) == 0)
    assert(three.compareTo(queen) < 0)
    assert(queen.compareTo(three) > 0)
  }
}
