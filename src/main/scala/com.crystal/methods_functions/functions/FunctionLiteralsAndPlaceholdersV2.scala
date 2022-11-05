package com.crystal.methods_functions.functions

import com.crystal.methods_functions.StockRecord

object FunctionLiteralsAndPlaceholdersV2 extends App {
  var readFinanceData = () => {
    val source = io.Source.fromFile("src/main/resources/GOOG.csv")
    for {
      line <- source.getLines().drop(1).toVector
      cols = line.split(",").map(_.trim)
    } yield {
      StockRecord(cols(0),
        cols(1).toFloat,
        cols(2).toFloat,
        cols(3).toFloat,
        cols(4).toFloat,
        cols(5).toFloat,
        cols(6).toDouble
      )

    }
  }
  val data = readFinanceData();

  val getDailyDela = (openPrice: Double, closePrice: Double,
                      delta: (Double, Double) => Double) => delta(openPrice, closePrice)

  val record = data.filter(_.date == "2020-01-30").head
  //println(getDailyDela(record.open, record.close,(open, close)=> open - close))
  // or
  println(getDailyDela(record.open, record.close, _ - _))
}
