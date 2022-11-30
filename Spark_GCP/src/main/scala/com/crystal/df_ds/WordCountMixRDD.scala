package com.crystal.df_ds

import com.crystal.rdd.Path
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, lower}

object WordCountMixRDD {
  case class Book(value: String)

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)

    val spark = SparkSession.builder()
      .appName("Word count")
      .master("local[*]")
      .getOrCreate()

    //Read each line of my book on DataSet
    import spark.implicits._
    val ds = spark.sparkContext
      .textFile(Path.u_dataPath + "book.txt")
      .flatMap(_.split("\\W+"))
      .filter(_.nonEmpty)
      .toDS()

    ds.select(lower($"value").alias("word"))
      .groupBy("word")
      .count()
      .sort(col("count").desc)
      .show(ds.count().toInt)


  }
}
