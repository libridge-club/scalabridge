package scalabridge.exceptions

final case class SuitException(
    private val message: String =
      "This suit does not exist. Please use the correct abbreviation."
) extends RuntimeException(message)
