package scalabridge

import scalabridge.exceptions.auction.*

import scala.jdk.javaapi.CollectionConverters
import scala.util.Failure
import scala.util.Success
import scala.util.Try

/**
  * The rules for Bridge Auction are as follows (paraphrasing/quoting the 2017 Laws of Bridge - LAWS 17-22):
  * 
  * - The first call is made by the dealer
  * - Every other call is made by the player who goes next from the last caller
  * - The auction ends after 4 consecutive passes (this only happens when they are the first 4 calls)
  * - The auction ends after 3 consecutive passes if a bid has already been made.
  * 
  * - Pass is always a valid call
  * - Double (X) can be called if and only if the last non-pass call was made by the opponents AND it was a bid.
  * - Redouble (XX) can be called if and only if the last non-pass call was made by the opponents AND it was a double (X).
  * - The first bid is freely chosen from all bids. All other bids must supersede the last bid (and consequently all previous ones).
  *
  */
case class Auction(
    dealer: Direction,
    calls: List[Call] = List.empty
) extends Validated[Auction] {

  override def getValid(): Either[Iterable[Throwable], Auction] = {
    val intermediateCalls = calls.zipWithIndex
      .map((callToTry, index) =>
        (
          Auction(this.dealer, calls.drop(index + 1)),
          callToTry,
          this.currentTurn.next((index + 1) * 3)
        )
      )
    val failures = intermediateCalls
      .map((auction, callToTry, directionToTry) => auction.makeCall(directionToTry, callToTry))
      .collect { case Failure(throwable) => throwable }
    if (failures.isEmpty) Right(this)
    else Left(failures)
  }

  import Auction.*

  def this(dealer: Direction) = this(dealer, List.empty)

  private lazy val lastNonPassCall: Option[(Call, Direction)] = {
    val callWithIndexOption = calls.zipWithIndex.find(!_._1.isPass)
    callWithIndexOption match
      case None => None
      case Some((call, index)) => {
        val positionsFromDealer = calls.size - index - 1
        Some(call, dealer.next(positionsFromDealer))
      }
  }
  private lazy val lastBidOption: Option[Bid] = calls.collectFirst({ case x: Bid =>
    x.asInstanceOf[Bid]
  })

  private val currentTurn: Direction = dealer.next(calls.size)

  private def isDoubleValid = lastNonPassCall match
    case None                    => false
    case Some((call, direction)) => call.isBid && directionIsOpponent(direction)

  private def isRedoubleValid = lastNonPassCall match
    case None                    => false
    case Some((call, direction)) => call.isDouble && directionIsOpponent(direction)

  private def directionIsOpponent(direction: Direction) =
    Side.getFromDirection(direction) != Side.getFromDirection(currentTurn)

  private def copyWithCall(call: Call) = this.copy(calls = call +: this.calls)

  val isFinished: Boolean = calls.equals(numberOfInitialPassesToEndAuction) ||
    (calls.startsWith(numberOfConsecutivePassesToEndAuction) && lastNonPassCall.isDefined)

  def getCalls(): java.util.List[Call] = CollectionConverters.asJava(calls)
  def makeCall(direction: Direction, call: Call): Try[Auction] = {
    if (this.isFinished) Failure(new AuctionAlreadyFinishedException)
    else if (currentTurn != direction) Failure(new CallInAnotherPlayersTurnException)
    else
      call match
        case PassingCall => Success(copyWithCall(call))
        case DoubleCall =>
          if (isDoubleValid) Success(copyWithCall(call)) else Failure(new InvalidCallException)
        case RedoubleCall =>
          if (isRedoubleValid) Success(copyWithCall(call)) else Failure(new InvalidCallException)
        case bid: Bid =>
          lastBidOption match
            case None => Success(copyWithCall(call))
            case Some(lastBid) =>
              if (bid.supersedes(lastBid)) Success(copyWithCall(call))
              else Failure(new InsufficientBidException)
  }
  def makeCallSafe(direction: Direction, call: Call): Auction =
    makeCall(direction, call).getOrElse(this)
  def getFinalContract: Option[Contract] = {
    if (!isFinished) None
    else {
      lastBidOption match
        case None => Some(AllPassContract)
        case Some(lastBid) => {
          val callsAfterLastBid = calls.span(_ != lastBid)._1
          val isDoubled = callsAfterLastBid.contains(DoubleCall)
          val isRedoubled = callsAfterLastBid.contains(RedoubleCall)
          val penaltyStatus =
            if (isRedoubled) PenaltyStatus.REDOUBLED
            else if (isDoubled) PenaltyStatus.DOUBLED
            else PenaltyStatus.NONE
          Some(Contract(lastBid.oddTricks, lastBid.strain, penaltyStatus))
        }
    }
  }
}
object Auction {
  def apply(dealer: Direction) = new Auction(dealer)
  private val numberOfInitialPassesToEndAuction =
    List(PassingCall, PassingCall, PassingCall, PassingCall)
  private val numberOfConsecutivePassesToEndAuction = List(PassingCall, PassingCall, PassingCall)
}
