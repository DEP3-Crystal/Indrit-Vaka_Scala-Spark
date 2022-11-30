package com.crystal.ml_100k

import com.crystal.ml_100k.model.MovieRate
import com.crystal.rdd.Path
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._

object MostPopularMovieRDDMix {

  def deserialize(line: String) = {
    val entries = line.split("\t")
    MovieRate(entries(0).toInt, entries(1).toInt, entries(2).toByte, entries(3).toLong)
  }

  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)
    val spark = SparkSession.builder()
      .appName("Most popular movie")
      .master("local[*]")
      .getOrCreate()

        val schema = new StructType()
          .add("userId", IntegerType, nullable = true)
          .add("movieId", IntegerType, nullable = true)
          .add("rating", ByteType, nullable = true)
          .add("timestamp", LongType, nullable = true)

    import spark.implicits._
    val moviesDs = spark.sparkContext
      .textFile(Path.u_dataPath + "ml-100k/u.data")
      .map(deserialize)
      .toDS()

    val row = moviesDs
      .select("movieId", "userId")
      .groupBy("movieId")
      .count()
      //      .sort(col("count").desc) //max 583
      .head()

    println(row)


  }

}
