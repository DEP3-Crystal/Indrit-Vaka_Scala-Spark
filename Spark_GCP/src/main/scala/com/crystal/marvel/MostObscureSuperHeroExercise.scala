package com.crystal.marvel

import com.crystal.rdd.Path
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import org.apache.spark.sql.{SparkSession, functions}

object MostObscureSuperHeroExercise {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)
    //Definition popular meaning who appeared the most with others in superheros book
    val spark = SparkSession.builder()
      .master("local[*]")
      .appName("Most popular heroes")
      .getOrCreate()

    val superHeroNamesSchema = new StructType()
      .add("id", IntegerType, nullable = true)
      .add("name", StringType, nullable = true)

    import spark.implicits._
    val names = spark.read
      .schema(superHeroNamesSchema)
      .option("sep", " ")
      .csv(Path.u_dataPath + "Marvel-names.txt")
      .as[SuperHerNames]

    val lines = spark.read
      .text(Path.u_dataPath + "Marvel-graph.txt")
      .as[SuperHero]

    val mostPopular = lines.withColumn("id", split(col("value"), " ")(0))
      .withColumn("connections", size(functions.split(col("value"), " ")) - 1)
      .groupBy("id")
      .agg(sum("connections").alias("connections"))

    val minValue = mostPopular.select("connections")
      .agg(min($"connections")).first()(0)
    names.join(mostPopular, "id")
      .filter($"connections" === minValue)
      .show()

  }
}
