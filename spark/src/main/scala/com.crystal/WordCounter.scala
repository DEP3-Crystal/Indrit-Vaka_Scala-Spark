package com.crystal

import org.apache.spark.{SparkConf, SparkContext}

object WordCounter {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[*]") //* is number of cores
      .setAppName("Word Counter")

    val sc: SparkContext = SparkContext.getOrCreate(conf)

    val textFile = sc.textFile("C:\\Spark\\README.md")
    println("Lines in file" + textFile.count())
    val words = textFile.flatMap(_.split(" "))
    val countPrep = words.map(word => (word, 1))
    val counts = countPrep.reduceByKey((accumValue, newValue) => accumValue + newValue)
    val sortedCounts = counts.sortBy(_._2, ascending = false)
    sortedCounts.saveAsTextFile("source\\sink\\wordCount.txt")


    //    sc.textFile("C:\\Spark\\README.md").flatMap(_.split(" ")).map(word => (word, 1)).reduceByKey((accumValue, newValue) => accumValue + newValue)
    //      .sortBy(_._2, ascending = false).saveAsTextFile("Spark/src/main/resources/sink/wordCount")

  }


}
