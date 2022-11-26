package com.crystal.spark.df_ds

import com.crystal.spark.rdd.Path
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{explode, lower, split}

object WordCount {
  case class Book(value: String)

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)

    val spark = SparkSession.builder()
      .appName("Word count")
      .master("local[*]")
      .getOrCreate()

    //Read each line of my book on DataSet
    import spark.implicits._
    val input = spark.read
    .text(Path.u_dataPath + "book.txt").as[Book]

    //Split using regular expression that extracts words
    val word =input.select(explode(split($"value","\\W+")).alias("word"))
      .filter($"word" =!= "")

    // Normalize everything to lowercase
    val wordToLower = word.select(lower($"word").alias("word"))

    val wordSortedCount = wordToLower.groupBy("word")
      .count()
      .sort("count")
    wordSortedCount.show(wordSortedCount.count().toInt) // here we are passing the number of rows we want to show, owr case all rows


  }
}
