package scalabridge.exceptions.auction

final class InsufficientBidException
    extends IllegalArgumentException("Your bid must supersede the last bid.")
