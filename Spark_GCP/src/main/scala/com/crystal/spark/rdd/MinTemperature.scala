package com.crystal.spark.rdd

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

object MinTemperature {

  def parseLine(line: String) = {
    val fields = line.split(",")
    val statusId = fields(0)
    val entryId = fields(2)
    val temperature = fields(3).toFloat * 0.1f * (7.0f / 5.0f) + 23.0f
    (statusId,entryId,temperature)
  }
4
  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc = new SparkContext("local[*]", "Min temp")

    val lines = sc.textFile(Path.u_dataPath + "1800.csv")
    //ITE00100554,18000101,TMAX,-75,,,E,
    lines.map(parseLine)
      .filter(_._2.equals("TMIN"))
      .map(x=>(x._1, x._3))
//      .min;
      .reduceByKey((acc, newValue)=> Math.min(acc,newValue))
      .collect()
      .foreach(res=>{
        val station = res._1
        val temp = res._2
        val formattedTemp = f"$temp%.2f"
        println(s"$station minimum temp: $formattedTemp")
      })
  }

}
