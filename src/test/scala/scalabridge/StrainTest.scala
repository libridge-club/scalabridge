package scalabridge

import org.junit.jupiter.api.Test
import scalabridge.exceptions.StrainException
import scala.util.Success

@Test
class StrainTest extends UnitFlatSpec {
  val clubs = Strain.CLUBS
  val notrumps = Strain.NOTRUMPS
  "A Strain" should "get from suit" in {
    assertResult(Strain.CLUBS)(Strain.fromSuit(Suit.CLUBS))
  }
  "A Strain" should "get from name" in {
    assertResult(Success(Strain.CLUBS))(Strain.fromName("c"))
    assertResult(Success(Strain.CLUBS))(Strain.fromName("C"))
    assertResult(Success(Strain.NOTRUMPS))(Strain.fromName("N"))
  }
  "A Strain" should "return the correct exception when fromName fails" in {
    val myIllegalStrain = "x"
    val exception: StrainException =
      Strain.fromName(myIllegalStrain).failed.get.asInstanceOf[StrainException]
    assertResult(myIllegalStrain)(exception.illegalStrain)
  }
  "A Strain" should "have order" in {
    assert(clubs.compareTo(clubs) == 0)
    assert(clubs.compareTo(notrumps) < 0)
    assert(notrumps.compareTo(clubs) > 0)
  }
}
