package scalabridge

import org.junit.jupiter.api.Test
import org.scalatest.EitherValues

@Test
class PBNUtilsTest extends UnitFunSpec with EitherValues { // FIXME should uncomment the tests that makes sense
  // val handWithCompleteCards = PBNUtils.handFromPartialDealTag("A6.KT2.K85.Q9742").get
  // val completeCards = CompleteHand(handWithCompleteCards)
  // val lessCards = CompleteHand(PBNUtils.handFromPartialDealTag("86.KT2.K85.Q974").get)
  // val moreCards = CompleteHand(PBNUtils.handFromPartialDealTag("86.KT2.K85.Q97432").get)
  // val completeCardsWrongChars = CompleteHand(PBNUtils.handFromPartialDealTag("8F.KT2.K8U.Q9742").get)
  // val completeCardsWrongChars2 = CompleteHand(PBNUtils.handFromPartialDealTag("P6.KT2.K85.Q9742").get)
  // val handWithAceOfSpades = completeCards
  // val handWithoutAceOfSpades = CompleteHand(PBNUtils.handFromPartialDealTag("K6.KT2.K85.Q9742").get)
  // val aceOfSpades = Card(Suit.SPADES, Rank.ACE)
  // describe("A CompleteHand") {
  //   it("should be valid when constructed using a pbnString") {
  //     assert(completeCards.getValid().isRight)
  //   }
  //   it("should not be valid when created with a different number of cards.") {
  //     lessCards.getValid().left.value.head shouldBe an[IllegalArgumentException]
  //     moreCards.getValid().left.value.head shouldBe an[IllegalArgumentException]
  //   }
  //   it("should not be valid when created with wrong characters for ranks.") {
  //     completeCardsWrongChars
  //       .getValid()
  //       .left
  //       .value
  //       .head shouldBe an[IllegalArgumentException]
  //     completeCardsWrongChars2
  //       .getValid()
  //       .left
  //       .value
  //       .head shouldBe an[IllegalArgumentException]
  //   }
  //   it("should have its hand accessible") {
  //     assert(completeCards.hand == handWithCompleteCards)
  //   }
  //   describe("containsCard function") {
  //     it("should return if there is a specific card inside the hand") {
  //       assert(handWithAceOfSpades.containsCard(aceOfSpades))
  //       assert(!handWithoutAceOfSpades.containsCard(aceOfSpades))
  //     }
  //   }
  //   describe("cards function") {
  //     it("should return the correct set of cards") {
  //       val actualSet = completeCards.cards
  //       val expectedSet = Set(
  //         Card(Suit.SPADES, Rank.ACE),
  //         Card(Suit.SPADES, Rank.SIX),
  //         Card(Suit.HEARTS, Rank.KING),
  //         Card(Suit.HEARTS, Rank.TEN),
  //         Card(Suit.HEARTS, Rank.TWO),
  //         Card(Suit.DIAMONDS, Rank.KING),
  //         Card(Suit.DIAMONDS, Rank.EIGHT),
  //         Card(Suit.DIAMONDS, Rank.FIVE),
  //         Card(Suit.CLUBS, Rank.QUEEN),
  //         Card(Suit.CLUBS, Rank.NINE),
  //         Card(Suit.CLUBS, Rank.SEVEN),
  //         Card(Suit.CLUBS, Rank.FOUR),
  //         Card(Suit.CLUBS, Rank.TWO)
  //       )
  //       actualSet shouldBe expectedSet
  //     }
  //   }
  // }

  // This one was from HandBuilder
  // @Test
  //   public void myTest() {
  //       String first = "q5.kt85.qjt8632.";
  //       HandBuilder handBuilder = new HandBuilder();
  //       Card sq = new Card(Suit.getSPADES(), Rank.getQUEEN());
  //       Card s5 = new Card(Suit.getSPADES(), Rank.getFIVE());
  //       Card s3 = new Card(Suit.getSPADES(), Rank.getTHREE());
  //       Card ht = new Card(Suit.getHEARTS(), Rank.getTEN());
  //       Card d8 = new Card(Suit.getDIAMONDS(), Rank.getEIGHT());
  //       Card ca = new Card(Suit.getCLUBS(), Rank.getACE());

  //       Hand firstHand = handBuilder.buildFromDotSeparatedString(first);

  //       assertTrue(firstHand.containsCard(sq));
  //       assertTrue(firstHand.containsCard(s5));
  //       assertFalse(firstHand.containsCard(s3));
  //       assertTrue(firstHand.containsCard(ht));
  //       assertTrue(firstHand.containsCard(d8));
  //       assertFalse(firstHand.containsCard(ca));
  //   }
}
