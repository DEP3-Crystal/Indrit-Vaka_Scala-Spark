package com.crystal.big_query

import com.crystal.utils.SessionProvider

object ShakespeareWords {
  def main(args: Array[String]): Unit = {

    //gcloud dataproc jobs submit spark --cluster=my-cluster --region=us-central1 --class=org.my.main.Class --jars=my_jar1.jar,my_jar2.jar -- arg1 arg2
    //gcloud dataproc jobs submit spark --cluster=spark --region=us-central1 --class=com.crystal.big_query.ShakespeareWords --jars=gs://indrit/word_count/spark_gcp_2.12-0.1.0-SNAPSHOT.jar
    val spark = SessionProvider.createSession("Reading from bq")


    //    val df = spark.read.bigquery("bigquery-public-data.samples.shakespeare")


    //The execution is faster as only the result is transmitted over the wire. In a similar way,
    // the queries can include JOINs more efficiently than running joins on Spark or use other
    // BigQuery features such as Subqueries, BigQuery User-defined Functions, Wildcard Tables, BigQuery ML, etc.
    //
    //In order to use this feature the following configurations MUST be set:
    //
    //“viewsEnabled” must be set to true.
    //“materializationDataset” must be set to a dataset where the GCP user has table creation permission. “materializationProject” is optional.
    //Fur further information on reading data from BigQuery query, visit here.
    //
    //Step 4: Writing Data to BigQuery
    spark.conf.set("viewsEnabled", "true")
    //    spark.conf.set("materializationDataset", "<dataset>")

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
