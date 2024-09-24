package scalabridge

import org.junit.jupiter.api.Test
import scalabridge.exceptions.SuitException
import scala.util.Success
import scala.util.Failure

@Test
class SuitTest extends UnitFunSpec {
  val clubs = Suit.CLUBS
  val diamonds = Suit.DIAMONDS
  describe("A Suit") {
    it("should be serializable toString") {
      clubs.toString shouldBe "c"
      diamonds.toString shouldBe "d"
    }
    it("should get from abbreviation with Try") {
      Suit.getFromAbbreviation('c') shouldBe Success(Suit.CLUBS)
      Suit.getFromAbbreviation('C') shouldBe Success(Suit.CLUBS)
      Suit.getFromAbbreviation('h') shouldBe Success(Suit.HEARTS)
    }
    it("should return the correct exception when getFromAbbreviation fails") {
      val myIllegalSuit = 'x'
      val exception: SuitException =
        Suit.getFromAbbreviation(myIllegalSuit).failed.get.asInstanceOf[SuitException]
      exception.illegalSuit shouldBe myIllegalSuit
    }
    it("should have order") {
      clubs.compareTo(clubs) shouldBe 0
      clubs.compareTo(diamonds) should be < 0
      diamonds.compareTo(clubs) should be > 0
    }
  }
}