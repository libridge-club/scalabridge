package club.libridge.libridgebackend.core.events

import club.libridge.libridgebackend.core.Card
import club.libridge.libridgebackend.core.Direction

import java.time.Instant

case class PlayCardEvent(timestamp:Instant, direction:Direction, card:Card) extends DealEvent{}
