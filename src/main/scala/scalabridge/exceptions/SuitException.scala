package scalabridge.exceptions

final case class SuitException(val illegalSuit: Char)
    extends IllegalArgumentException(
      s"The suit ${illegalSuit} does not exist. Please use the correct abbreviation."
    )
