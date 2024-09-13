package scalabridge.exceptions

final case class StrainException(val illegalStrain: String)
    extends IllegalArgumentException(
      s"The strain ${illegalStrain} does not exist. Please use the correct abbreviation."
    )
