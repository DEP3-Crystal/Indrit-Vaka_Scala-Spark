package com.crystal.rdd

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

object TotalSpendsByCustomer {
  def parseLine(line:String) = {
    val entries = line.split(",")
    //id,prod,amount
    //44,8602,37.19
    val id = entries(0).toInt
    val cost = entries(2).toDouble
    (id,cost)
  }
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext("local[*]","Customer spends")
    val lines = sc.textFile(Path.u_dataPath + "customer-orders.csv")

    lines.map(parseLine)
      .reduceByKey((acc,newVal)=>acc+newVal)
      .sortBy(_._2,ascending = false)
      .map(value=> (value._1,f"${value._2}%.2f"))
      .collect()
      .foreach(println)
  }

}
