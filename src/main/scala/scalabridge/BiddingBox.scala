package scalabridge

import scala.collection.immutable.HashMap

object BiddingBox {

  private val PASS_STRING = "P";
  private val DOUBLE_STRING = "X";
  private val REDOUBLE_STRING = "XX";
  private val labelToBidMap: HashMap[String, Bid] = HashMap(
    (
      for {
        oddTricks <- OddTricks.values; strain <- Strain.values
      } yield getMapKey(oddTricks, strain) -> Bid(oddTricks, strain)
    )* // splat operator
  )
  private val labelToCallMap: HashMap[String, Call] = HashMap(
    PASS_STRING -> PassingCall,
    DOUBLE_STRING -> DoubleCall,
    REDOUBLE_STRING -> RedoubleCall
  ) ++
    labelToBidMap

  private def getMapKey(oddTricks: OddTricks, strain: Strain) =
    (oddTricks.getSymbol + strain.getSymbol).toUpperCase
  def getPass: Call = PassingCall
  def getDouble: Call = DoubleCall
  def getRedouble: Call = RedoubleCall
  def getOption(label: String): Option[Call] =
    labelToCallMap.get(label.toUpperCase)
  def getBid(oddTricks: OddTricks, strain: Strain): Bid =
    labelToBidMap(getMapKey(oddTricks, strain))
}
