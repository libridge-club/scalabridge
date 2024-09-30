package scalabridge.exceptions

final class InvalidPBNDealTagException
    extends IllegalArgumentException(
      "This PBN Deal Tag is invalid. Please refer to Section 3.4.11 of the PBN Standard 2.1"
    )
