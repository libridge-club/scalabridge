package scalabridge

import org.junit.jupiter.api.Test

@Test
class CallTest extends UnitFlatSpec {
  val fourSpadesBid = Bid(OddTricks.FOUR, Strain.SPADES)
  val threeSpadesBid = Bid(OddTricks.THREE, Strain.SPADES)
  val fourNoTrumpsBid = Bid(OddTricks.FOUR, Strain.NOTRUMPS)
  "A Bid" should "be comparable" in {
    assert(fourSpadesBid.compareTo(fourSpadesBid) == 0)
    assert(fourSpadesBid.compareTo(threeSpadesBid) > 0)
    assert(fourSpadesBid.compareTo(fourNoTrumpsBid) < 0)
  }
  "A Bid" should "be serializable toString" in {
    assertResult("4S")(fourSpadesBid.toString)
    assertResult("4N")(fourNoTrumpsBid.toString)
    assertResult("3S")(threeSpadesBid.toString)
  }
  "A Bid" should "have getters for java interoperability" in {
    assertResult(OddTricks.FOUR)(fourSpadesBid.getOddTricks)
    assertResult(Strain.SPADES)(fourSpadesBid.getStrain)
  }
}
