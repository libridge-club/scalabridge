package scalabridge.exceptions

final case class StrainException(
    private val message: String =
      "This strain does not exist. Please use the correct abbreviation."
) extends RuntimeException(message)
