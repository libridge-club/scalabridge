package club.libridge.libridgebackend.core


enum EffectiveDuplicateBoardNumber extends Enum[EffectiveDuplicateBoardNumber]:
    import EffectiveDuplicateBoardNumber._
    case ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, ELEVEN, TWELVE, THIRTEEN, FOURTEEN, FIFTEEN, SIXTEEN
    def next = getNext(this)
end EffectiveDuplicateBoardNumber
object EffectiveDuplicateBoardNumber:
    private val COMPLETE_CYCLE_SIZE = EffectiveDuplicateBoardNumber.values.size
    private val FIRST_DUPLICATE_BOARD_NUMBER = EffectiveDuplicateBoardNumber.values(0)
    private val LAST_DUPLICATE_BOARD_NUMBER = EffectiveDuplicateBoardNumber.values(COMPLETE_CYCLE_SIZE - 1)
    
    def fromPositiveInteger(positiveInteger:PositiveInteger) = {
        val mod = positiveInteger.number % COMPLETE_CYCLE_SIZE
        val finalNumber = if(mod<=0) COMPLETE_CYCLE_SIZE else mod
        EffectiveDuplicateBoardNumber.fromOrdinal(finalNumber-1)
    }
    private def getNext(effectiveDuplicateBoardNumber:EffectiveDuplicateBoardNumber) = {
        if(LAST_DUPLICATE_BOARD_NUMBER == effectiveDuplicateBoardNumber)
            FIRST_DUPLICATE_BOARD_NUMBER
        else
            EffectiveDuplicateBoardNumber.fromOrdinal(effectiveDuplicateBoardNumber.ordinal + 1)
    }
end EffectiveDuplicateBoardNumber