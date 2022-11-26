package com.crystal.spark.df_ds

object SparkSQLDatasets {

  def main(args: Array[String]): Unit = {
    val spark = FakeFriendsSparkSession.getSession
    val teenagers = spark.sql("SELECT * FROM people WHERE age >= 13 AND age <= 19")
    teenagers.collect()
      .foreach(println)

    spark.stop()

  }

}
