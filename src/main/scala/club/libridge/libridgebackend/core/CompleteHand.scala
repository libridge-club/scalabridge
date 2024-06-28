package club.libridge.libridgebackend.core

case class CompleteHand(pbnString: String):
    import CompleteHand._

    private val cards = getCardsFromPbnString(pbnString) match {
        case Left(exception) => throw exception
        case Right(cardsSet) => cardsSet
    }

    private val numberOfCards = this.cards.size
    if (numberOfCards < GameConstants.NUMBER_OF_TRICKS_IN_A_COMPLETE_HAND || numberOfCards > GameConstants.NUMBER_OF_TRICKS_IN_A_COMPLETE_HAND)
        throw new IllegalArgumentException(INVALID_NUMBER_OF_CARDS)

    def hasCard(card:Card): Boolean = this.cards.contains(card)

    override def toString: String = this.pbnString

end CompleteHand
case object CompleteHand:
    private val SYMBOL_TO_RANK_MAP = Rank.values().map( (rank) => (rank.getSymbol().charAt(0) -> rank) ).toMap
    private val ORDER_OF_SUITS_MAP = Map(
        0 -> Suit.SPADES,
        1 -> Suit.HEARTS,
        2 -> Suit.DIAMONDS,
        3 -> Suit.CLUBS,
    )
    private val SEPARATOR_CHAR:Char = '.'
    private val INVALID_RANK_SYMBOL = "Cannot create hand with invalid rank symbol."
    private val INVALID_NUMBER_OF_CARDS = s"A complete hand must have ${GameConstants.NUMBER_OF_TRICKS_IN_A_COMPLETE_HAND} cards."

    private def getCardsFromPbnString(pbnString:String):Either[IllegalArgumentException, Set[Card]] = {
        val suits = pbnString.split(SEPARATOR_CHAR);
        val (exceptions, cards) = suits
            .zipWithIndex
            .toSet
            .map{ case (suit, index) => createCardsFromStringAndSuit(suit,ORDER_OF_SUITS_MAP(index)) }
            .partitionMap(identity)
        if(exceptions.nonEmpty)
            return Left(exceptions.head)
        else
            return Right(cards.fold(Set.empty)(_ union _))
    }

    private def createCardsFromStringAndSuit(cardsString:String, suit:Suit): Either[IllegalArgumentException, Set[Card]] = {
        val (exceptions, cards) = cardsString.toSet
            .map( (rankSymbol) => getCardOptionFromRankSymbolAndSuit(rankSymbol, suit) )
            .partitionMap(identity)
        if(exceptions.nonEmpty)
            return Left(exceptions.head)
        else
            return Right(cards)
    }

    private def getCardOptionFromRankSymbolAndSuit(rankSymbol:Char, suit: Suit): Either[IllegalArgumentException, Card] = {
        SYMBOL_TO_RANK_MAP.get(rankSymbol) match {
            case Some(rank) => Right(new Card(suit,rank))
            case None => Left(new IllegalArgumentException(INVALID_RANK_SYMBOL))
        }
    }
end CompleteHand