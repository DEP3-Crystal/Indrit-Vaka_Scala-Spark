package com.crystal.df_ds

import org.apache.spark.sql.functions.col

object NumberOfFriends {
  def main(args: Array[String]): Unit = {
    val sparkSession = FakeFriendsSparkSession.getSession
    val people = FakeFriendsSparkSession.getDataset(sparkSession)
    people.select("name").show()

    println("People greater than 21: ")
    people.filter(people("age") < 21).show()

    println("Grouping by age")
    people.groupBy("age").count()
      .sort(col("count").desc)
      .show()

    println("Make everyone 10 years older: ")
    people.select(people("name"), (people("age") + 10).alias("age")).show()
    // another way
    people.withColumn("newAge",col("age")+10).show()
    sparkSession.stop()
  }

}
