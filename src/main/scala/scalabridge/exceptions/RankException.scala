package scalabridge.exceptions

final case class RankException(val illegalRank: Char)
    extends IllegalArgumentException(
      s"The rank ${illegalRank} does not exist. Please use the correct abbreviation."
    )
