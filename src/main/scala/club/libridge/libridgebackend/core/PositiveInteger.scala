package club.libridge.libridgebackend.core

case class PositiveInteger(number:Int) extends Validated[PositiveInteger]:
    import PositiveInteger._
    override def getValid(): Either[ List[Exception], PositiveInteger ] = {
        if(this.number<=0){
            Left(List(new IllegalArgumentException(NUMBER_SHOULD_BE_POSITIVE)));
        } else {
            Right(this)
        }
    }
end PositiveInteger
object PositiveInteger :
    private val NUMBER_SHOULD_BE_POSITIVE = "Number should be greater than 0."
end PositiveInteger