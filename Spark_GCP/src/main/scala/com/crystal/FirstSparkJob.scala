package com.crystal

import org.apache.spark.sql.SparkSession

object FirstSparkJob {

  //running form terminal
  //cd Spark_GCP
  //  sbt package
  // cd target scala-2.12
  //spark-submit --master "local[*]"  --class com.crystal.FirstSparkJob spark_gcp_2.12-0.1.0-SNAPSHOT.jar

  //Run from clouding
  // spark-submit --master yarn --class com.crystal.FirstSparkJob spark_gcp_2.12-0.1.0-SNAPSHOT.jar
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("First GCP DataProc Job")
      .master("local[*]")
      .getOrCreate()

    val rdd = spark.sparkContext.parallelize(1 to 5)
    rdd.collect().foreach(println)
    println("Spark config")
    spark.conf.getAll.foreach(println)


  }
}
