package club.libridge.libridgebackend.core

case class CompleteHand(pbnString: String) extends Validated[CompleteHand]:
    import CompleteHand._

    override def getValid(): Either[Iterable[Exception], CompleteHand] = validate(this.pbnString)
    val cards = getCardsFromPbnString(this.pbnString).getOrElse(Set.empty)
    def hasCard(card:Card): Boolean = this.cards.contains(card)
    override def toString: String = this.pbnString

end CompleteHand
case object CompleteHand:
    private val ORDER_OF_SUITS_MAP = Map(
        0 -> Suit.SPADES,
        1 -> Suit.HEARTS,
        2 -> Suit.DIAMONDS,
        3 -> Suit.CLUBS,
    )
    private val SEPARATOR_CHAR:Char = '.'
    private def INVALID_NUMBER_OF_CARDS(pbnString:String) = s"Failed with hand: ${pbnString}. A complete hand must have ${GameConstants.SIZE_OF_HAND} cards."

    private def validate(pbnString: String): Either[Iterable[Exception], CompleteHand] = {
        getCardsFromPbnString(pbnString) match {
            case Left(exceptions) => Left(exceptions)
            case Right(cards) => {
                if (cards.size == GameConstants.SIZE_OF_HAND)
                    Right(CompleteHand(pbnString))
                else
                    Left(Iterable(new IllegalArgumentException(INVALID_NUMBER_OF_CARDS(pbnString))))
            }
        }

    }

    private def getCardsFromPbnString(pbnString:String):Either[Set[IllegalArgumentException], Set[Card]] = {
        val suits = pbnString.split(SEPARATOR_CHAR);
        val (exceptions, cards) = suits
            .zipWithIndex
            .toSet
            .map{ case (suit, index) => createCardsFromStringAndSuit(suit,ORDER_OF_SUITS_MAP(index)) }
            .partitionMap(identity)
        if(exceptions.nonEmpty)
            Left(exceptions.fold(Set.empty)(_ union _))
        else
            Right(cards.fold(Set.empty)(_ union _))
    }

    private def createCardsFromStringAndSuit(cardsString:String, suit:Suit): Either[Set[IllegalArgumentException], Set[Card]] = {
        val (exceptions, cards) = cardsString.toSet
            .map( (rankSymbol) => getCardOptionFromRankSymbolAndSuit(rankSymbol, suit) )
            .partitionMap(identity)
        if(exceptions.nonEmpty)
            Left(exceptions)
        else
            Right(cards)
    }

    private def getCardOptionFromRankSymbolAndSuit(rankSymbol:Char, suit: Suit): Either[IllegalArgumentException, Card] = {
        try {
            val rank = Rank.getFromAbbreviation(rankSymbol)
            Right(new Card(suit,rank))
        } catch {
            case e: Throwable => Left(new IllegalArgumentException(e.getMessage))
        }
    }
end CompleteHand