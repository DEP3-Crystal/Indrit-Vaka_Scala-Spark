package com.crystal.gcp

import org.apache.spark.sql.SparkSession
import org.apache.spark.util.LongAccumulator

object SparkAccumulators {
  //spark-submit --master yarn --deploy-mode client --class com.crystal.gcp.SparkAccumulators spark_gcp_2.12-0.1.0-SNAPSHOT.jar /user/indrit/datasets/flight-data/2010-summary.csv
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("Spark Accumulators")
      .getOrCreate()

    val df = spark.read.option("header", "true")
      .option("inferSchema", "true")
      .csv(args(0))

    //Unnamed
    val unitedStatesCount = new LongAccumulator
    spark.sparkContext.register(unitedStatesCount)

    //Named accumulator
    val indiaFlightsCount = new LongAccumulator
    spark.sparkContext.register(indiaFlightsCount, "India accumulator")

    //2nd way for creating accumulator
    val chinaFlightsCount = spark.sparkContext.longAccumulator("China accumulator")

    df.foreach {
      row => {
        val destination = row(0).toString
        val origin = row(1).toString
        val count = row(2).toString.toLong

        if (destination.equalsIgnoreCase("united states") || origin.equalsIgnoreCase("united states")) {
          unitedStatesCount.add(count)
        }
        if (destination.equalsIgnoreCase("india") || origin.equalsIgnoreCase("india")) {
          indiaFlightsCount.add(count)
        }
        if (destination.equalsIgnoreCase("china") || origin.equalsIgnoreCase("china")) {
          chinaFlightsCount.add(count)
        }
      }
    }

    println(s"-------------------- United States: $unitedStatesCount")
    println(s"-------------------- India: $indiaFlightsCount")
    println(s"-------------------- China: $chinaFlightsCount")

  }

}
