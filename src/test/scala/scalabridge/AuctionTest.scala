package scalabridge

import org.junit.jupiter.api.Test
import scalabridge.nonpure.ContractFromTextValidatedBuilder
import scala.collection.immutable.SortedSet
import scalabridge.exceptions.auction.CallInAnotherPlayersTurnException
import scalabridge.exceptions.auction.AuctionAlreadyFinishedException
import scala.util.Success
import scalabridge.exceptions.auction.InvalidCallException
import scalabridge.exceptions.auction.InsufficientBidException

@Test
class AuctionTest extends UnitFunSpec {
  describe("An Auction") {
    val anyDirection = Direction.NORTH
    it("should get dealer") {
      Auction(Direction.NORTH).dealer shouldBe Direction.NORTH
      Auction(Direction.EAST).dealer shouldBe Direction.EAST
    }
    it("should get a java.util.list of calls for java interoperability") {
      Auction(anyDirection).getCalls() shouldBe a[java.util.List[?]]
    }
    it("should return exception when a player calls out of turn") {
      Auction(anyDirection)
        .makeCall(anyDirection.next, PassingCall)
        .failed
        .get shouldBe a[CallInAnotherPlayersTurnException]
    }
    it("should add a Call when a player makeCall with a valid Call") {
      val auctionWithPass = Auction(anyDirection).makeCall(anyDirection, PassingCall)
      auctionWithPass shouldBe Success(Auction(anyDirection, List(PassingCall)))
    }
    it("should add a Call when a player makeCallSafe with a valid Call") {
      val auctionWithPass = Auction(anyDirection).makeCallSafe(anyDirection, PassingCall)
      auctionWithPass shouldBe Auction(anyDirection, List(PassingCall))
    }
    it("should return exception when a player makeCall with an invalid call") {
      val auctionWithPass = Auction(anyDirection).makeCall(anyDirection, PassingCall).get
      auctionWithPass
        .makeCall(anyDirection.next, DoubleCall)
        .failed
        .get shouldBe an[InvalidCallException]
      auctionWithPass
        .makeCall(anyDirection.next, RedoubleCall)
        .failed
        .get shouldBe an[InvalidCallException]
    }
    it("should return exception when a player makeCall with an insufficient bid") {
      val oneNTBid = Bid(OddTricks.ONE, Strain.NOTRUMPS)
      val oneClubBid = Bid(OddTricks.ONE, Strain.CLUBS)
      val auctionWith1NT = Auction(anyDirection).makeCall(anyDirection, oneNTBid).get
      auctionWith1NT
        .makeCall(anyDirection.next, oneClubBid)
        .failed
        .get shouldBe an[InsufficientBidException]
    }
    it("should return exception when a player doubles a partner bid") {
      val oneClubBid = Bid(OddTricks.ONE, Strain.CLUBS)
      val auctionWith1ClubAndPass = Auction(anyDirection)
        .makeCall(anyDirection, oneClubBid)
        .get
        .makeCall(anyDirection.next, PassingCall)
        .get
      auctionWith1ClubAndPass
        .makeCall(anyDirection.next(2), DoubleCall)
        .failed
        .get shouldBe an[InvalidCallException]
    }
    it("should return exception when a player redoubles a partner double") {
      val oneClubBid = Bid(OddTricks.ONE, Strain.CLUBS)
      val auctionWith1ClubAndDoubleAndPass = Auction(anyDirection)
        .makeCall(anyDirection, oneClubBid)
        .get
        .makeCall(anyDirection.next, DoubleCall)
        .get
        .makeCall(anyDirection.next(2), PassingCall)
        .get
      auctionWith1ClubAndDoubleAndPass
        .makeCall(anyDirection.next(3), RedoubleCall)
        .failed
        .get shouldBe an[InvalidCallException]
    }
    describe("when it has no calls") {
      val emptyAuction = Auction(anyDirection)
      it("getCalls should be empty") {
        emptyAuction.getCalls() shouldBe empty
      }
      it("should not be finished") {
        emptyAuction.isFinished shouldBe false
      }
      it("should return no Contract") {
        emptyAuction.getFinalContract(VulnerabilityStatus.NONVULNERABLE) shouldBe None
      }
    }
    describe("when finished") {
      val finishedAuction = Auction(Direction.NORTH)
        .makeCallSafe(Direction.NORTH, PassingCall)
        .makeCallSafe(Direction.EAST, PassingCall)
        .makeCallSafe(Direction.SOUTH, PassingCall)
        .makeCallSafe(Direction.WEST, PassingCall)
      it("should be finished") {
        finishedAuction.isFinished shouldBe true
      }
      it("should return exception when a player makeCall") {
        finishedAuction
          .makeCall(Direction.NORTH, PassingCall)
          .failed
          .get shouldBe an[AuctionAlreadyFinishedException]
      }
      it("should return itself when a player makeCallSafe") {
        finishedAuction.makeCallSafe(Direction.NORTH, PassingCall) shouldBe finishedAuction
      }
      it("should return its Contract") {
        finishedAuction.getFinalContract(VulnerabilityStatus.NONVULNERABLE) shouldBe Some(
          AllPassContract
        )
        val otherFinishedAuction = Auction(Direction.NORTH)
          .makeCall(Direction.NORTH, Bid(OddTricks.FOUR, Strain.SPADES))
          .get
          .makeCall(Direction.EAST, DoubleCall)
          .get
          .makeCall(Direction.SOUTH, PassingCall)
          .get
          .makeCall(Direction.WEST, PassingCall)
          .get
          .makeCall(Direction.NORTH, PassingCall)
          .get
        otherFinishedAuction.getFinalContract(VulnerabilityStatus.NONVULNERABLE) shouldBe
          Some(
            Contract(
              OddTricks.FOUR,
              Strain.SPADES,
              PenaltyStatus.DOUBLED,
              VulnerabilityStatus.NONVULNERABLE
            )
          )
      }
    }
  }
}
