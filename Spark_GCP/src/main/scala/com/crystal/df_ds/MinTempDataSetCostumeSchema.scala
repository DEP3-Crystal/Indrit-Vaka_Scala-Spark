package com.crystal.df_ds

import com.crystal.rdd.Path
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.functions.{col, lit, round}
import org.apache.spark.sql.types._
import org.apache.spark.sql.{SparkSession, functions}

object MinTempDataSetCostumeSchema {
  case class Temperature(stationID: String, date: Int, measure_type: String, temperature: Float)

  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)

    val spark = SparkSession.builder()
      .appName("Min Temp")
      .master("local[*]")
      .getOrCreate()

    val temSchema = new StructType()
      .add("stationID", StringType, nullable = true)
      .add("date", IntegerType, nullable = true)
      .add("measure_type", StringType, nullable = true)
      .add("temperature", FloatType, nullable = true)

    // read the file as dataset
    import spark.implicits._
    val ds = spark.read
      .schema(temSchema)
      .csv(Path.u_dataPath + "1800.csv")
      .as[Temperature]

    ds.filter($"measure_type" === "TMIN")
      .select("stationID", "temperature") //select only fields we really need
      .groupBy("stationID")
      .min()
      //withColumn Replaces or creates new col
      .withColumn("temperature", round($"min(temperature)" * 0.1f, 2))
      .sort(col("temperature"))
      .select(col("stationID"),
        functions.concat(col("temperature"), lit(" C")).alias("temp"))
      .show()
  }

}
