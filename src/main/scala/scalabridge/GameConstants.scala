package scalabridge

object GameConstants {
  def instance: GameConstants.type = this
  def NUMBER_OF_HANDS: Int = 4
  def SIZE_OF_HAND: Int = 13
  def NUMBER_OF_TRICKS_IN_A_COMPLETE_HAND: Int = SIZE_OF_HAND
  def COMPLETE_TRICK_NUMBER_OF_CARDS: Int = NUMBER_OF_HANDS
}
