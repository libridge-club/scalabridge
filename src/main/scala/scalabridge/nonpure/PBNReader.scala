package scalabridge.nonpure

import scala.io.Source
import scalabridge.Auction
import scalabridge.Direction
import util.control.Breaks._
import scala.collection.mutable.ArrayBuffer

object PBNReader {

  val auctionTagRegex = s"""\\[Auction "([NESW]+)"\\]""".r
  val allPassCallTokenRegex = s"AP".r
  val onePassCallTokenRegex = s"Pass".r
  val doubleCallTokenRegex = s"X".r
  val redoubleCallTokenRegex = s"XX".r
  val notPlayerTurnCallTokenRegex = s"-".r
  val contractCallTokenRegex = "[1234567]{1}(NT|N|S|H|D|C)(X|XX)*".r

  def isCallToken(candidate : String) = {
    val returnValue = allPassCallTokenRegex.matches(candidate) ||
    onePassCallTokenRegex.matches(candidate) ||
    doubleCallTokenRegex.matches(candidate) ||
    redoubleCallTokenRegex.matches(candidate) ||
    notPlayerTurnCallTokenRegex.matches(candidate) ||
    contractCallTokenRegex.matches(candidate)
    returnValue
  }


  def printFullAuction(allAuctionLines: ArrayBuffer[String]): Unit = {
    allAuctionLines.foreach(println)
    println
  }

  def createAuction(allAuctionLines: ArrayBuffer[String]): Auction = {
    val direction = allAuctionLines(0) match
          case auctionTagRegex(directionString) => Direction.getFromAbbreviation(directionString.charAt(0)).get
          case _ => throw new IllegalArgumentException
    // println(s"Direction string is -${directionString}-")
    println(s"Direction is: ${direction}")
    val x = allAuctionLines.remove(0)
    println(x)
    val callTokens = allAuctionLines
    .map( line => line.split(" +").filter(s => isCallToken(s)))
    .flatten
    callTokens.foreach(println)
    Auction(direction)
  }

  def readFromFile(filename: String): List[Auction] = {
    val resource = Source.fromResource(filename) // Side-effect
    val lines: Iterator[String] = resource.getLines
    var allAuctionLines: ArrayBuffer[String] = ArrayBuffer.empty
    var auctionLine: String = ""
    val allAuctions: ArrayBuffer[Auction] = ArrayBuffer.empty

    while (lines.hasNext) {
      breakable {
        val currentLine = lines.next()
        if (!currentLine.startsWith("[Auction")) {
          break
        } else {
          allAuctionLines = ArrayBuffer.empty
          allAuctionLines.addOne(currentLine)
          auctionLine = lines.next()
          while (!auctionLine.startsWith("[")) {
            allAuctionLines.addOne(auctionLine)
            auctionLine = lines.next()
          }
          printFullAuction(allAuctionLines)
          if(allAuctionLines.size > 1 ) allAuctions.addOne(createAuction(allAuctionLines))
        }
      }
    }
    allAuctions.toList
  }

}


// [Auction "N"]
// Pass  1D    Pass  1H
// Pass  1N    Pass  2C =1= 
// Pass  2S    Pass  4H
// Pass  Pass  Pass