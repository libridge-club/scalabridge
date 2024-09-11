package scalabridge.exceptions

final case class RankException(
    private val message: String = "This rank does not exist. Please use the correct abbreviation."
) extends RuntimeException(message)
