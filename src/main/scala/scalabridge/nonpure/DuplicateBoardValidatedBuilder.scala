package scalabridge.nonpure

import scalabridge.DuplicateBoard
import scalabridge.PositiveInteger
import scalabridge.EffectiveDuplicateBoardNumber
import scalabridge.Direction
import scalabridge.CompleteHand
import scalabridge.CompleteDeckInFourHands

object DuplicateBoardValidatedBuilder {

  def build(boardNumber: Int, pbnDealTag: String): DuplicateBoard = {
    try {
      val positiveInteger =
        PositiveIntegerValidatedBuilder.build(PositiveInteger(boardNumber))
      val first = pbnDealTag.charAt(0)
      val firstDirection = Direction.getFromAbbreviation(first)
      val hands = pbnDealTag.substring(2).split(" ")
      val completeHands =
        hands.map((handString) => CompleteHandValidatedBuilder.build(CompleteHand(handString)))
      val directionToCompleteHandMap = completeHands.zipWithIndex
        .map((hand, index) => (firstDirection.next(index) -> hand))
        .toMap
      val finalHands = CompleteDeckInFourHandsValidatedBuilder.build(
        CompleteDeckInFourHands(directionToCompleteHandMap)
      )
      DuplicateBoard(positiveInteger, finalHands)
    } catch {
      case illegalArgumentException: IllegalArgumentException =>
        throw illegalArgumentException
      case exception: Exception =>
        throw new IllegalArgumentException(exception.getMessage())
    }
  }

}
