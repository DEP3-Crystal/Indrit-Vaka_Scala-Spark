package com.crystal.spark.df_ds

import org.apache.spark.sql.functions.{avg, col, round}

object FriendsByAge {

  def main(args: Array[String]): Unit = {
    val sparkSession = FakeFriendsSparkSession.getSession
    val people = FakeFriendsSparkSession.getDataset(sparkSession)

    val friendsByAge = people
      .select("age", "friends")

    friendsByAge.groupBy("age")
      .avg("friends")
      .sort(col("age").desc).show()

    friendsByAge
      .groupBy("age")
      .agg(round(avg("friends"), 2).alias("friends_avg"))
      .sort(col("age"))
      .show()
  }

}
