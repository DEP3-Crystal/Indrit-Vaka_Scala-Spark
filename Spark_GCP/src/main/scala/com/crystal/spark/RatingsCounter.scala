package com.crystal.spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

object RatingsCounter {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc = new SparkContext("local[*]", "RatingsCounter")
    val sourcePath = "source/"
    val lines = sc.textFile(sourcePath + "ml-100k/u.data")
    val ratings = lines.map(_.split("\t")(2))
    val result = ratings.countByValue()
    val sortedResults = result.toSeq.sortWith(_._2 > _._2)

    sortedResults.foreach(println)

  }
}
