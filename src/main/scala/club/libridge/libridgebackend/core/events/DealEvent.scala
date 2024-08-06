package club.libridge.libridgebackend.core.events

import club.libridge.libridgebackend.core.Direction

/**
 * Reserved for Events related to players at a table.
 * The players are referenced by their direction.
 *
 * Examples are playing a card, making a call or requesting an undo.
 */
trait DealEvent extends Event {
  val direction:Direction
}
