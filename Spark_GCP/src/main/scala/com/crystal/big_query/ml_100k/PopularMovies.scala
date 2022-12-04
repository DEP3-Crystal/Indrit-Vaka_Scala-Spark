package com.crystal.big_query.ml_100k

import com.crystal.rdd.Path
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.desc
import org.apache.spark.sql.types.{ByteType, IntegerType, LongType, StructType}

object PopularMovies {
  case class Movie(movieId: Int);

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
    val moviesDs = spark.read
      .option("sep", "\t")
      .schema(schema)
      .csv(Path.u_dataPath + "ml-100k/u.data")
      .as[Movie]

    val topMovieIds = moviesDs.groupBy("movieId")
      .count()
      .orderBy(desc("count"))

    topMovieIds.show(10)

  }

}
