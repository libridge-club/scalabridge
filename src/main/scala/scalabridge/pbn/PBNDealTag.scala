package scalabridge.pbn

import scalabridge.Direction
import scalabridge.Hand
import scalabridge.Validated
import scalabridge.exceptions.InvalidPBNDealTagException

import scala.util.Failure
import scala.util.Success
import scala.util.Try
import scala.util.matching.Regex

/**
  * This is defined in Section 3.4.11  The Deal tag
  * of the 2.1 PBN Standard
  * 
  * "<first>:<1st_hand> <2nd_hand> <3rd_hand> <4th_hand>"
  *
  * @param dealTag
  */
final case class PBNDealTag(dealTag: String) extends Validated[PBNDealTag] {
  import PBNDealTag.*

  override def getValid(): Either[Iterable[Throwable], PBNDealTag] =
    if (completeRegex.matches(this.dealTag))
      Right(this)
    else
      Left(List(new InvalidPBNDealTagException))

  def getParts: Try[(Direction, List[Option[Hand]])] = {
    this.getValid() match
      case Left(throwableList) => Failure(throwableList.head)
      case Right(value) => {
        this.dealTag match
          case completeRegex(a, b, c, d, e) => {
            val direction = Direction.getFromAbbreviation(a.charAt(0)).get
            val allHandsStrings = List(b, c, d, e)
            val handOptions = allHandsStrings.map(handString =>
              if (emptyHandString.equals(handString)) None
              else PBNUtils.handFromPartialDealTag(handString)
            )
            Success(direction, handOptions)
          }
          case _ => Failure(new InvalidPBNDealTagException)
      }
  }
}

object PBNDealTag {
  private val emptyHandString: String = "-"
  private val firstRegex: String = s"[NESW]{1}"
  private val oneSuitRegex: String = s"[AKQJT98765432]*"
  private val emptyHandRegex: String = "-"
  private val nonEmptyHandRegex: String = s"${oneSuitRegex}\\.${oneSuitRegex}\\.${oneSuitRegex}\\.${oneSuitRegex}"
  private val nonEmptyHandRegexWithMatchers: String = s"(${oneSuitRegex})\\.(${oneSuitRegex})\\.(${oneSuitRegex})\\.(${oneSuitRegex})"
  private val oneHandRegex: String =
    s"${emptyHandRegex}|${nonEmptyHandRegex}"
  private val completeRegex: Regex =
    s"^(${firstRegex}):(${oneHandRegex}) (${oneHandRegex}) (${oneHandRegex}) (${oneHandRegex})$$".r
  def getOneSuitRegex = oneSuitRegex
  def getNonEmptyHandRegex = nonEmptyHandRegexWithMatchers
}
