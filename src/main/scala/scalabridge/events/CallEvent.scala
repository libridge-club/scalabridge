package scalabridge.events

import scalabridge.{Call, Direction}

import java.time.Instant

case class CallEvent(timestamp: Instant, direction: Direction, call: Call)
    extends DealEvent {}
