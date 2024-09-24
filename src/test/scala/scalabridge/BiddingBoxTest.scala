package scalabridge

import org.junit.jupiter.api.Test

@Test
class BiddingBoxTest extends UnitFunSpec {
  val PASS_STRING: String = "P";
  val DOUBLE_STRING: String = "X";
  val REDOUBLE_STRING: String = "XX";
  val THREE_NO_TRUMPS_STRING: String = "3N";
  describe("A BiddingBox") {
    it("should get all non bid calls") {
      BiddingBox.getPass shouldBe PassingCall
      BiddingBox.getOption(PASS_STRING) shouldBe Some(PassingCall)
      BiddingBox.getDouble shouldBe DoubleCall
      BiddingBox.getOption(DOUBLE_STRING) shouldBe Some(DoubleCall)
      BiddingBox.getRedouble shouldBe RedoubleCall
      BiddingBox.getOption(REDOUBLE_STRING) shouldBe Some(RedoubleCall)
    }
    it("should get bid calls from label") {
      BiddingBox.getOption(THREE_NO_TRUMPS_STRING) shouldBe Some(
        Bid(OddTricks.THREE, Strain.NOTRUMPS)
      )
    }
    it("should get bid calls from oddTricks and Strain") {
      BiddingBox.getBid(OddTricks.THREE, Strain.NOTRUMPS) shouldBe Bid(
        OddTricks.THREE,
        Strain.NOTRUMPS
      )
    }
  }
}
