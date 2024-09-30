package scalabridge.exceptions

import scalabridge.GameConstants

final case class InvalidCompleteHandException(val partialDealTagString: String)
    extends IllegalArgumentException(
      s"Failed with hand: ${partialDealTagString}. A complete hand must have ${GameConstants.SIZE_OF_HAND} cards."
    )
