package scalabridge.exceptions

final class TrickAlreadyFullException
    extends IllegalArgumentException("You cannot play a card to a trick that is already full.")
