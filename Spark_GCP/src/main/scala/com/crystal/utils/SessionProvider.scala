package com.crystal.utils

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object SessionProvider {

  def createSession(appName: String, master: String = "local[*]"): SparkSession = {
    Logger.getLogger("org").setLevel(Level.ERROR)
    SparkSession.builder()
      .appName(appName)
      .master(master)
      .getOrCreate()
  }

}
