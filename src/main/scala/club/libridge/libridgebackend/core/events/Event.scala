package club.libridge.libridgebackend.core.events

import java.time.Instant

/**
 * Reserved for all events in the server.
 * This goes from deal events, like playing a card
 * to kibitzer events like joining a table to
 * tournament director rulings, etc.
 */
trait Event {
  val timestamp:Instant
}