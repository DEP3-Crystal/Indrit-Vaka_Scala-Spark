package com.crystal.ml_100k

import com.crystal.ml_100k.model.MovieRate
import com.crystal.rdd.Path
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.desc
import org.apache.spark.sql.types._

object MostPopularMovieUsingJoin {
  case class Movie(movieId: Int, name: String, release: String, url: String)


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
    val moviesRatingDs = spark.read
      .option("sep", "\t")
      .schema(schema)
      .csv(Path.u_dataPath + "ml-100k/u.data")
      .as[MovieRate]

    val movieSchema = new StructType()
      .add("movieId", IntegerType, nullable = true)
      .add("name", StringType, nullable = true)
      .add("release", StringType, nullable = true)
      .add("other", StringType, nullable = true)
      .add("url", StringType, nullable = true)


    val movieRD = spark.read
      .option("sep", "|")
      .schema(movieSchema)
      .csv(Path.u_dataPath + "ml-100k/u.item")
      .as[Movie]

    movieRD.join(moviesRatingDs, "movieId")
      .groupBy("movieId","name","release")
      .count()
      .orderBy(desc("count"))
      .show()
  }

}
