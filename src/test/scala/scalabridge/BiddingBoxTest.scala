package scalabridge

import org.junit.jupiter.api.Test

@Test
class BiddingBoxTest extends UnitFlatSpec {
  val PASS_STRING: String = "P";
  val DOUBLE_STRING: String = "X";
  val REDOUBLE_STRING: String = "XX";
  val THREE_NO_TRUMPS_STRING: String = "3N";
  "A BiddingBox" should "get all non bid calls" in {
    assertResult(PassingCall)(BiddingBox.getPass)
    assertResult(PassingCall)(BiddingBox.getOption(PASS_STRING).get)
    assertResult(DoubleCall)(BiddingBox.getDouble)
    assertResult(DoubleCall)(BiddingBox.getOption(DOUBLE_STRING).get)
    assertResult(RedoubleCall)(BiddingBox.getRedouble)
    assertResult(RedoubleCall)(BiddingBox.getOption(REDOUBLE_STRING).get)
  }
  "A BiddingBox" should "get bid calls from label" in {
    assertResult(Bid(OddTricks.THREE, Strain.NOTRUMPS))(
      BiddingBox.getOption(THREE_NO_TRUMPS_STRING).get
    )
  }
  "A BiddingBox" should "get bid calls from oddTricks and Strain" in {
    assertResult(Bid(OddTricks.THREE, Strain.NOTRUMPS))(
      BiddingBox.getBid(OddTricks.THREE, Strain.NOTRUMPS)
    )
  }
}
