package com.crystal.big_querry

import com.crystal.utils.SessionProvider
import com.google.cloud.spark.bigquery._

object ShakespeareWords {
  def main(args: Array[String]): Unit = {
    val spark = SessionProvider.createSession("Reading from bq")

    // Use the Cloud Storage bucket for temporary BigQuery export data used
    // by the connector. This assumes the Cloud Storage connector for
    // Hadoop is configured.
    val bucket = spark.sparkContext.hadoopConfiguration.get("fs.gs.system.bucket")
    spark.conf.set("temporaryGcsBucket", bucket)

    // Load data in from BigQuery.
    val wordsDF = spark.read.bigquery("bigquery-public-data.samples.shakespeare").cache()
    wordsDF.show()
    wordsDF.printSchema()
    wordsDF.createOrReplaceTempView("words")

    // Perform word count.
    val wordCountDF = spark.sql(
      "SELECT word, SUM(word_count) AS word_count FROM words GROUP BY word")

    // Saving the data to BigQuery
    wordCountDF.write.bigquery("wordcount_dataset.wordcount_output")


  }
}
