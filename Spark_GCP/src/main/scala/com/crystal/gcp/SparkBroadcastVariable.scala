package com.crystal.gcp

import org.apache.spark.sql.SparkSession

object SparkBroadcastVariable {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Spark Broadcast variable example")
      //      .master("local[*]")
      .getOrCreate()

    val users = spark.sparkContext.textFile(args(0))
    val songs = spark.sparkContext.textFile(args(1))
    val userSongPlayCount = spark.sparkContext.textFile(args(2))

    val usersMap = users.zipWithIndex().map(user => (user._1, user._2))
      .collectAsMap()

    val songsMap = songs.map(_.split(" ")).map(song => song(0) -> song(1))
      .collectAsMap()

    val userSoundsCountArray = userSongPlayCount.map(ele => ele.split("\t"))

    //broadcast var
    val usersBroadcast = spark.sparkContext.broadcast(usersMap)
    val songBroadcast = spark.sparkContext.broadcast(songsMap)

    val modifiedCounts = userSoundsCountArray.map {
      case Array(uid, sid, count) =>
        val user = usersBroadcast.value.getOrElse(uid, 0)
        val song = songBroadcast.value.getOrElse(sid, 0)
        //        val user =usersMap.getOrElse(uid, 0)
        //        val song =songsMap.getOrElse(sid, 0)
        (user, song, count)
    }
    modifiedCounts.take(50).foreach(println)


  }
}
