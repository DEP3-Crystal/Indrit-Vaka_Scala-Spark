package com.crystal

import org.apache.spark.{SparkConf, SparkContext}

object WordCounter {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[*]") //* is number of cores
      .setAppName("Word Counter").set("spark.speculation","false")

    val sc: SparkContext = SparkContext.getOrCreate(conf)

    val textFile = sc.textFile("C:\\Spark\\README.md")
    println("Lines in file" + textFile.count())
    val words = textFile.flatMap(_.split(" "))
      .filter(containsNonWord)
    val countPrep = words.map(word => (word, 1))
    val counts = countPrep.reduceByKey((accumValue, newValue) => accumValue + newValue)
    val sortedCounts = counts.sortBy(_._2, ascending = false)
    //      .foreach(println)
    sortedCounts.saveAsTextFile("source\\sink\\wordCount.txt")
    scala.io.Source


    //    sc.textFile("C:\\Spark\\README.md").flatMap(_.split(" ")).map(word => (word, 1)).reduceByKey((accumValue, newValue) => accumValue + newValue)
    //      .sortBy(_._2, ascending = false).saveAsTextFile("Spark/src/main/resources/sink/wordCount")

  }

  def containsNonWord(word: String) = {
    val pattern = raw"\\W".r
    pattern.matches(word)
  }

}
