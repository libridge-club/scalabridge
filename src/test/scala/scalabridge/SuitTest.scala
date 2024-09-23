package scalabridge

import org.junit.jupiter.api.Test
import scalabridge.exceptions.SuitException
import scala.util.Success
import scala.util.Failure

@Test
class SuitTest extends UnitFlatSpec {
  val clubs = Suit.CLUBS
  val diamonds = Suit.DIAMONDS
  "A Suit" should "be serializable toString" in {
    assertResult("c")(clubs.toString)
    assertResult("d")(diamonds.toString)
  }
  "A Suit" should "get from abbreviation with Try" in {
    assertResult(Success(Suit.CLUBS))(Suit.getFromAbbreviation('c'))
    assertResult(Success(Suit.CLUBS))(Suit.getFromAbbreviation('C'))
    assertResult(Success(Suit.HEARTS))(Suit.getFromAbbreviation('h'))
  }
  "A Suit" should "return the correct exception when getFromAbbreviation fails" in {
    val myIllegalSuit = 'x'
    val exception: SuitException =
      Suit.getFromAbbreviation(myIllegalSuit).failed.get.asInstanceOf[SuitException]
    assertResult(myIllegalSuit)(exception.illegalSuit)
  }
  "A Suit" should "have order" in {
    assert(clubs.compareTo(clubs) == 0)
    assert(clubs.compareTo(diamonds) < 0)
    assert(diamonds.compareTo(clubs) > 0)
  }
}
