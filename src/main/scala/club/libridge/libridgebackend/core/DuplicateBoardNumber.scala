package club.libridge.libridgebackend.core


enum DuplicateBoardNumber extends Enum[DuplicateBoardNumber]:
    import DuplicateBoardNumber._
    case ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, ELEVEN, TWELVE, THIRTEEN, FOURTEEN, FIFTEEN, SIXTEEN
    def next = getNextDuplicateBoardNumber(this)
end DuplicateBoardNumber
object DuplicateBoardNumber:
    private val MIN_BOARD_NUMBER = 1;
    private val COMPLETE_CYCLE_SIZE = DuplicateBoardNumber.values.size
    private val FIRST_DUPLICATE_BOARD_NUMBER = DuplicateBoardNumber.values(0)
    private val LAST_DUPLICATE_BOARD_NUMBER = DuplicateBoardNumber.values(COMPLETE_CYCLE_SIZE - 1)
    
    def fromPositiveInteger(positiveInteger:PositiveInteger) = {
        val mod = positiveInteger.number % COMPLETE_CYCLE_SIZE
        val finalNumber = if(mod<=0) COMPLETE_CYCLE_SIZE else mod
        DuplicateBoardNumber.fromOrdinal(finalNumber-1)
    }
    private def getNextDuplicateBoardNumber(duplicateBoardNumber:DuplicateBoardNumber) = {
        if(LAST_DUPLICATE_BOARD_NUMBER == duplicateBoardNumber)
            FIRST_DUPLICATE_BOARD_NUMBER
        else
            DuplicateBoardNumber.fromOrdinal(duplicateBoardNumber.ordinal + 1)
    }
end DuplicateBoardNumber