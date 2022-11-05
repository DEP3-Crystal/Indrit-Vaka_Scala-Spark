package com.crystal.methods_functions.functions

import com.crystal.methods_functions.StockRecordV2

import scala.io.BufferedSource

object PartialFunctionsUsingRecordsV2 extends App {

  val readFinanceData = {
    val source: BufferedSource = io.Source.fromFile("src/main/resources/stockMarketData.csv")
    for {
      line <- source.getLines().drop(1).toVector
      cols = line.split(",").map(_.trim)}
    yield StockRecordV2(cols(0),
      cols(1).toFloat,
      cols(2).toFloat,
      cols(3).toFloat,
      cols(4).toFloat,
      cols(5))
  }
  val data = readFinanceData

  var printStockRecords: PartialFunction[String, Unit] = {

    case x: String if List("MSFT", "GOOG", "TM", "TTM", "DB", "BNS").contains(x) =>
      for (line <- data.filter(_.ticker == x)) {
        println(s"Date: ${line.date} Ticker: ${line.ticker} Close: ${line.close}")
      }

  }
  if (printStockRecords.isDefinedAt("MSFT")) printStockRecords("MSFT")
  // Map will not use is defined method
  //  List("DB", "TSLA") map {printStockRecords}
  List("DB", "TSLA").collect {
    printStockRecords
  }

}
