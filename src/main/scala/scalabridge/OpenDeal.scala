package scalabridge

import scalabridge.events.Event

case class OpenDeal(
    duplicateBoard: DuplicateBoard,
    private val laterFirstDealEvents: List[Event]
) {

  def addEvent(dealEvent: Event): OpenDeal = {
    this.copy(laterFirstDealEvents = (dealEvent +: this.laterFirstDealEvents))
  }

  /** @return
    *   A Vector with the Deal Events. This is a vector to allow a client to
    *   resume reading the Events from a specific point in case they want it.
    */
  def getDealEvents: Vector[Event] = {
    this.laterFirstDealEvents.reverse.toVector
  }
}
case object OpenDeal {
  def empty(duplicateBoard: DuplicateBoard): OpenDeal =
    OpenDeal(duplicateBoard, List.empty)
}
