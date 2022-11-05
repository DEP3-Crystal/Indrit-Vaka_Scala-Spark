package com.crystal.methods_functions.functions

import com.crystal.methods_functions.StockRecordV2

import scala.io.BufferedSource

object PartialFunctionsUsingRecords extends App {

  val readFinanceData = {
    val source: BufferedSource = io.Source.fromFile("src/main/resources/stockMarketData.csv")
    for {
      line <- source.getLines().drop(1)
      cols = line.split(",").map(_.trim)}
    yield StockRecordV2(cols(0),
      cols(1).toFloat,
      cols(2).toFloat,
      cols(3).toFloat,
      cols(4).toFloat,
      cols(5))
  }
  val data = readFinanceData

  val printStockRecords = new PartialFunction[String, Unit] {

    val recordedTickers: List[String] = List("MSFT", "GOOG", "TM", "TTM", "DB", "BNS")

    override def apply(x: String): Unit =
      for (line <- data.filter(_.ticker == x)) {
        println(s"Date; ${line.date}- Ticker: ${line.ticker} Close: ${line.close}")
      }

    override def isDefinedAt(x: String): Boolean = recordedTickers.contains(x)
  }
  if (printStockRecords.isDefinedAt("MSFT")) printStockRecords("MSFT")
}
