package club.libridge.libridgebackend.core.events

import club.libridge.libridgebackend.core.{Call, Direction}

import java.time.Instant

case class CallEvent(timestamp:Instant, direction:Direction, call:Call) extends DealEvent{}
