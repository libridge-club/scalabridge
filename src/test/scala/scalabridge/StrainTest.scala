package scalabridge

import org.junit.jupiter.api.Test
import scalabridge.exceptions.StrainException
import scala.util.Success

@Test
class StrainTest extends UnitFunSpec {
  val clubs = Strain.CLUBS
  val notrumps = Strain.NOTRUMPS
  describe("A Strain") {
    it("should get from suit") {
      Strain.fromSuit(Suit.CLUBS) shouldBe Strain.CLUBS
    }
    it("should get from name") {
      Strain.fromName("c") shouldBe Success(Strain.CLUBS)
      Strain.fromName("C") shouldBe Success(Strain.CLUBS)
      Strain.fromName("N") shouldBe Success(Strain.NOTRUMPS)
    }
    it("should return the correct exception when fromName fails") {
      val myIllegalStrain = "x"
      val exception: StrainException =
        Strain.fromName(myIllegalStrain).failed.get.asInstanceOf[StrainException]
      exception.illegalStrain shouldBe myIllegalStrain
    }
    it("should have order") {
      clubs.compareTo(clubs) shouldBe 0
      clubs.compareTo(notrumps) should be < 0
      notrumps.compareTo(clubs) should be > 0
    }
  }
}
