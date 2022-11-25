package com.crystal.spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

object WordCount extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)

  val sc = new SparkContext("local[*]","Word count")

  val lines =sc.textFile(Path.sourcePath + "book.txt")
  lines.flatMap(_.split("\\W+"))
    .map(_.trim)
    .filter(_.nonEmpty)
    .filter(_.length>2)
    .map(_.toLowerCase)
    .map(x=>(x,1))
    .reduceByKey((x,y)=> x + y)
    .sortBy(_._2, false)
    .collect()
//    .sortWith(_._2>_._2)
    .foreach(println)
}
