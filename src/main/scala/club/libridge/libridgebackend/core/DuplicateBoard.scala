package club.libridge.libridgebackend.core

/**
 * The rules for Duplicate Boards are defined in the 2017 Laws of Bridge - LAW 2
 */
case class DuplicateBoard(number: DuplicateBoardNumber, hands: CompleteDeckInFourHands):
    import DuplicateBoard._

    def getDealer(): Direction = getDealerFromDuplicateBoardNumber(this.number)
    def isVulnerable(side: Side): Boolean = vulnerabilityStatus(this.number, side)
    def getHandOf(direction:Direction): CompleteHand = hands.getHandOf(direction)

end DuplicateBoard
case object DuplicateBoard:

    private def DuplicateBoardNumberFromInt = (number:Int) => DuplicateBoardNumber.fromPositiveInteger(PositiveInteger(number))

    /**
     * North Dealer Boards 1 5  9 13
     * East  Dealer Boards 2 6 10 14
     * South Dealer Boards 3 7 11 15
     * West  Dealer Boards 4 8 12 16
     */
    private def getDealerFromDuplicateBoardNumber(number:DuplicateBoardNumber): Direction = {
        val mod = number.ordinal() % Direction.values.size;
        Direction.NORTH.next(mod);
    }

    /**
     * Neither Side Vulnerable Boards 1 8 11 14
     * North-South  Vulnerable Boards 2 5 12 15
     * East-West    Vulnerable Boards 3 6  9 16
     * Both Sides   Vulnerable Boards 4 7 10 13
     */
    private val northSouthVulnerabilitySet = Set(2,5,12,15,4,7,10,13).map(DuplicateBoardNumberFromInt)
    private val eastWestVulnerabilitySet = Set(3,6,9,16,4,7,10,13).map(DuplicateBoardNumberFromInt)
    private def vulnerabilityStatus(boardNumber:DuplicateBoardNumber, side: Side): Boolean = {
        side match {
            case Side.NORTHSOUTH => northSouthVulnerabilitySet.contains(boardNumber)
            case Side.EASTWEST => eastWestVulnerabilitySet.contains(boardNumber)
        }
    }

end DuplicateBoard