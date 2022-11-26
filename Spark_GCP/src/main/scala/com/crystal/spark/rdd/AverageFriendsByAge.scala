package com.crystal.spark.rdd

import org.apache.log4j.{Level, Logger}
import org.apache.spark._

object AverageFriendsByAge {

  def parseLine(line: String): (Int, Int) = {
    val entries = line.split(",")
    // id, name, age, friends
    val id: Int = entries(0).toInt
    val name = entries(1)
    val age = entries(2).toInt
    val friends = entries(3).toInt
    (age, friends)
  }

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc = new SparkContext("local[*]", "Friends by age")
    val lines = sc.textFile(Path.u_dataPath + "fakefriends-noheader.csv")
    lines.map(parseLine)
      .mapValues(x => (x, 1))
      .reduceByKey((accumulatedValue, newValue) => (accumulatedValue._1 + newValue._1, accumulatedValue._2 + newValue._2))
      .mapValues(x=> x._1/x._2)
      .collect().sortWith(_._2>_._2)
      .foreach(println)

  }

}
