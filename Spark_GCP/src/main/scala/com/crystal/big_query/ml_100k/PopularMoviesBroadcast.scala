package com.crystal.big_query.ml_100k

import com.crystal.big_query.ml_100k.model.MovieRate
import com.crystal.rdd.Path
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, udf}
import org.apache.spark.sql.types.{ByteType, IntegerType, LongType, StructType}

import scala.io.{Codec, Source}

object PopularMoviesBroadcast {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val spark = SparkSession.builder()
      .appName("Most popular movie")
      .master("local[*]")
      .getOrCreate()

    //the nameDic will be now available to each  node executors
    // NOTE: This will load all the data on memory
    val nameDic = spark.sparkContext.broadcast(loadMovieNames())


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
      .as[MovieRate]

    val movieCounts = moviesDs.groupBy("movieId").count()

    val lookupName :Int => String = (movieId:Int) =>{
      nameDic.value(movieId)
     }


    val lookupNameUDF = udf(lookupName)

    val moviesWithNames = movieCounts.withColumn("movieTitle", lookupNameUDF(col("movieId")))
    val sortedMoviesWithNames = moviesWithNames.sort("count")

    sortedMoviesWithNames.show(sortedMoviesWithNames.count().toInt, truncate = false)

  }

  def loadMovieNames(): Map[Int, String] = {
    implicit val codec: Codec = Codec("ISO-8859-1")

    var movieName: Map[Int, String] = Map()

    val lines = Source.fromFile(Path.u_dataPath + "ml-100k/u.item")

    for (line <- lines.getLines()) {
      val fields = line.split('|')
      if (fields.length > 1) {
        movieName += (fields(0).toInt -> fields(1))
      }
    }
    lines.close()

    movieName
  }
}
