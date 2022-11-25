package com.crystal.spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

object TotalSpends {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext("local[*]","Customer spends")
    val lines = sc.textFile(Path.sourcePath + "customer-orders.csv")

//    lines.map(parseLine)
  }
}
