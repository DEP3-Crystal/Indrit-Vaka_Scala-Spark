package com.crystal.df_ds

import com.crystal.rdd.Path
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{Dataset, SparkSession}

//This is the schema for owr data
case class Person(id: Int, name: String, age: Int, friends: Int)

object FakeFriendsSparkSession {

  private var dataSet: Option[Dataset[Person]] = None

  def getDataset(sparkSession: SparkSession): Dataset[Person] = {
    if (dataSet.isEmpty) {
      import sparkSession.implicits._

      dataSet = Option(sparkSession.read
        .option("header", "true")
        .option("inferSchema", "true")
        .csv(Path.u_dataPath + "fakefriends.csv")
        .as[Person]) // this will convert it to a dataSet
      dataSet.get.printSchema()
      dataSet.get.createOrReplaceTempView("people")
    }
    dataSet.get
  }

  def getSession: SparkSession = {
    Logger.getLogger("org").setLevel(Level.ERROR)

    val sparkSession = SparkSession.builder()
      .appName("Hello from Dataframes")
      .master("local[*]")
      .getOrCreate()

    val schemaPeople: Dataset[Person] = getDataset(sparkSession)
    sparkSession
  }

}
