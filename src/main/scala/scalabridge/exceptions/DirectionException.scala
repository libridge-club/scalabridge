package scalabridge.exceptions

final case class DirectionException(val illegalDirection: Char)
    extends IllegalArgumentException(
      s"The direction ${illegalDirection} does not exist. Please use the correct abbreviation."
    )
