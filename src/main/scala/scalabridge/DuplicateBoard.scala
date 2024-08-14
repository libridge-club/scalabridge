package scalabridge

/** The rules for Duplicate Boards are defined in the 2017 Laws of Bridge - LAW
  * 2 Even though the properties of the duplicate board number are equal modulo
  * 16, the law does not define an upper limit for this number, so we accept any
  * positive integer but do all of the property calculation using its
  * effectiveDuplicateBoardNumber
  */
case class DuplicateBoard(
    number: PositiveInteger,
    hands: CompleteDeckInFourHands
):
  import DuplicateBoard._

  private val effectiveBoardNumber =
    EffectiveDuplicateBoardNumber.fromPositiveInteger(number)
  def getDealer(): Direction = getDealerFromEffectiveDuplicateBoardNumber(
    this.effectiveBoardNumber
  )
  def isVulnerable(side: Side): Boolean =
    vulnerabilityStatus(this.effectiveBoardNumber, side)
  def getHandOf(direction: Direction): CompleteHand = hands.getHandOf(direction)

end DuplicateBoard
case object DuplicateBoard:

  /** North Dealer Boards 1 5 9 13 East Dealer Boards 2 6 10 14 South Dealer
    * Boards 3 7 11 15 West Dealer Boards 4 8 12 16
    */
  private def getDealerFromEffectiveDuplicateBoardNumber(
      number: EffectiveDuplicateBoardNumber
  ): Direction = {
    val mod = number.ordinal() % Direction.values.size;
    Direction.NORTH.next(mod);
  }

  /** Neither Side Vulnerable Boards 1 8 11 14 North-South Vulnerable Boards 2 5
    * 12 15 East-West Vulnerable Boards 3 6 9 16 Both Sides Vulnerable Boards 4
    * 7 10 13
    */
  private def effectiveDuplicateBoardNumberFromInt = (number: Int) =>
    EffectiveDuplicateBoardNumber.fromPositiveInteger(PositiveInteger(number))
  private val northSouthVulnerabilitySet =
    Set(2, 5, 12, 15, 4, 7, 10, 13).map(effectiveDuplicateBoardNumberFromInt)
  private val eastWestVulnerabilitySet =
    Set(3, 6, 9, 16, 4, 7, 10, 13).map(effectiveDuplicateBoardNumberFromInt)
  private def vulnerabilityStatus(
      boardNumber: EffectiveDuplicateBoardNumber,
      side: Side
  ): Boolean = {
    side match {
      case Side.NORTHSOUTH => northSouthVulnerabilitySet.contains(boardNumber)
      case Side.EASTWEST   => eastWestVulnerabilitySet.contains(boardNumber)
    }
  }

end DuplicateBoard
