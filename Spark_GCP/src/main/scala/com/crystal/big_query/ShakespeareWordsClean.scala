package com.crystal.big_query

import com.crystal.utils.SessionProvider

object ShakespeareWordsClean {
  def main(args: Array[String]): Unit = {

    //gcloud dataproc jobs submit spark --cluster=my-cluster --region=us-central1 --class=org.my.main.Class --jars=my_jar1.jar,my_jar2.jar -- arg1 arg2
    //gcloud dataproc jobs submit spark --cluster=spark --region=us-central1 --class=com.crystal.big_query.ShakespeareWords --jars=gs://indrit/word_count/spark_gcp_2.12-0.1.0-SNAPSHOT.jar
    val spark = SessionProvider.createSession("Reading from bq")

    spark.conf.set("credentialsFile", "C:\\GCP\\BigQuery\\bigquery-368715-c053ef03a5e3.json")
    spark.conf.set("viewsEnabled", "true")
    //    spark.conf.set("materializationDataset", "<dataset>")
    // bash function
    val sql =
    """
  SELECT tag, COUNT(*) c
  FROM (
    SELECT SPLIT(tags, '|') tags
    FROM `bigquery-public-data.stackoverflow.posts_questions` a
    WHERE EXTRACT(YEAR FROM creation_date)>=2014
  ), UNNEST(tags) tag
  GROUP BY 1
  ORDER BY 2 DESC
  LIMIT 10
  """
    val df = spark.read.format("bigquery").load(sql)
    df.show()
  }
}
