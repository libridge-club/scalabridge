package scalabridge.exceptions

final case class OddTricksException(val illegalLevel: String)
    extends IllegalArgumentException(
      s"The level ${illegalLevel} is invalid. Level should be between 1 and 7 inclusive."
    )
