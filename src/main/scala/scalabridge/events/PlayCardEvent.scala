package scalabridge.events

import scalabridge.Card
import scalabridge.Direction

import java.time.Instant

case class PlayCardEvent(timestamp: Instant, direction: Direction, card: Card) extends DealEvent {}
