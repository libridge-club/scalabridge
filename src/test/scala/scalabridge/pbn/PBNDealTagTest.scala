package scalabridge.pbn

import org.junit.jupiter.api.Test
import scalabridge.Direction
import scalabridge.Hand
import scalabridge.UnitFunSpec

@Test
class PBNDealTagTest extends UnitFunSpec {
  describe("A PBNDealTag") {
    describe("when valid") {
      val validDealTag1 = "W:KQT2.AT.J6542.85 - A8654.KQ5.T.QJT6 -"
      val subject1 = PBNDealTag(validDealTag1)
      val validDealTag2 = "E:86.KT2.K85.Q9742 KJT932.97.942.86 54.8653.AQJT73.3 AQ7.AQJ4.6.AKJT5"
      val subject2 = PBNDealTag(validDealTag2)
      it("should return itself when getValid is called") {
        subject1.getValid() shouldBe Right(subject1)
        subject2.getValid() shouldBe Right(subject2)
      }
      it("should return its parts starting from the -first- direction ") {
        val (direction, hands) = subject1.getParts.get
        direction shouldBe Direction.WEST
        val expectedHands = List("KQT2.AT.J6542.85", "-", "A8654.KQ5.T.QJT6", "-")
        hands(0).get.toString() shouldBe expectedHands(0)
        hands(1) shouldBe None
        hands(2).get.toString() shouldBe expectedHands(2)
        hands(3) shouldBe None

        val (direction2, hands2) = subject2.getParts.get
        direction2 shouldBe Direction.EAST
        val expectedHands2 =
          List("86.KT2.K85.Q9742", "KJT932.97.942.86", "54.8653.AQJT73.3", "AQ7.AQJ4.6.AKJT5")
        hands2(0).get.toString() shouldBe expectedHands2(0)
        hands2(1).get.toString() shouldBe expectedHands2(1)
        hands2(2).get.toString() shouldBe expectedHands2(2)
        hands2(3).get.toString() shouldBe expectedHands2(3)
      }
    }
  }
}
