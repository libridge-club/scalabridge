package scalabridge.exceptions.auction

final class CallInAnotherPlayersTurnException
    extends IllegalArgumentException("You cannot call in another player's turn.")
