package scalabridge.exceptions.auction

final class AuctionAlreadyFinishedException
    extends IllegalStateException("You cannot bid after the auction is finished.")
