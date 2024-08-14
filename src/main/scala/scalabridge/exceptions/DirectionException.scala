package scalabridge.exceptions

final case class DirectionException(
    private val message: String =
      "This direction does not exist. Please use the correct abbreviation."
) extends RuntimeException(message)
