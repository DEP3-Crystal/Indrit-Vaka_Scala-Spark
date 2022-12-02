# Setting up BigQuery with Spark

[https://hevodata.com/learn/spark-bigquery-connector/#step1](https://hevodata.com/learn/spark-bigquery-connector/#step1)

- [Apache Spark SQL connector for Google BigQuery](https://github.com/GoogleCloudDataproc/spark-bigquery-connector#reading-data-from-a-bigquery-table)
- [Hello world Project](ShakespeareWords.scala)

# Configuring the Google cloud CLI

[How Application Default Credentials works](https://cloud.google.com/docs/authentication/application-default-credentials)

# Logging in Goggle cloud using CLI

`gcloud auth login`

# Big query connection examples

[bigquery-connector-spark-example](https://cloud.google.com/dataproc/docs/tutorials/bigquery-connector-spark-example)

# List gCloud projects

`gcloud projects list`
more info [here](https://cloud.google.com/sdk/gcloud/reference/projects/list)

# Use a specific project

`gcloud config set project <project-id>`
view more [here](https://cloud.google.com/sdk/gcloud/)

# Check project we are using
`gcloud config get-value project`
[view more](https://stackoverflow.com/questions/63041888/how-to-check-which-gcloud-project-is-active)

# Copying a local jar to a bucket
`gsutil cp "jar location" <bucket location like (gs://indrit/word_count)>`

[view more](https://cloud.google.com/storage/docs/gsutil/commands/cp)

# Reading Data from a BigQuery Table

For reading data from a BigQuery table, you can refer to the following code blocks.

```scala worksheet
import com.crystal.utils.SessionProvider

val spark = SessionProvider.createSession("Reading from bq")

val df = spark.read
  .format("bigquery")
  .load("bigquery-public-data.samples.shakespeare")
```

or the Scala only implicit API:

```scala worksheet
import com.crystal.utils.SessionProvider

val spark = SessionProvider.createSession("Reading from bq")


import com.google.cloud.spark.bigquery._

val df = spark.read.bigquery("bigquery-public-data.samples.shakespeare")
```

# Reading Data from a BigQuery Query

The Spark BigQuery Connector lets you execute any Standard SQL SELECT query
on BigQuery and have the results sent directly to a Spark Dataframe.
This is simple to accomplish, as demonstrated by the following code sample:

```
spark.conf.set("viewsEnabled","true")
spark.conf.set("materializationDataset","<dataset>")

sql = """
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
df = spark.read.format("bigquery").load(sql)
df.show()
```

And the above code yields the following result:

A second option is to use the Query option in the following way:

```df = spark.read.format("bigquery").option("query", sql).load()```


he execution is faster as only the result is transmitted over the wire. In a similar way, the queries can include JOINs more efficiently than running joins on Spark or use other BigQuery features such as Subqueries, BigQuery User-defined Functions, Wildcard Tables, BigQuery ML, etc.

In order to use this feature the following configurations MUST be set:

“viewsEnabled” must be set to true.
“materializationDataset” must be set to a dataset where the GCP user has table creation permission. “materializationProject” is optional.
Fur further information on reading data from BigQuery query, visit [here](https://github.com/GoogleCloudDataproc/spark-bigquery-connector#reading-data-from-a-bigquery-query).


# Writing Data to BigQuery
view more [here](https://hevodata.com/learn/spark-bigquery-connector/#step4)
```
df.writeStream
.format("bigquery")
.option("temporaryGcsBucket","some-bucket")
.option("checkpointLocation", "some-location")
.option("table", "dataset.table")
```


# Submitting job

[gcloud dataproc jobs submit spark](https://cloud.google.com/sdk/gcloud/reference/dataproc/jobs/submit/spark#--jars)

EXAMPLES
To submit a Spark job that runs the main class of a jar, run:
```
gcloud dataproc jobs submit spark --cluster=my-cluster --region=us-central1 --jar=my_jar.jar -- arg1 arg2
```
To submit a Spark job that runs a specific class of a jar, run:
```
gcloud dataproc jobs submit spark --cluster=my-cluster --region=us-central1 --class=org.my.main.Class --jars=my_jar1.jar,my_jar2.jar -- arg1 arg2
```
To submit a Spark job that runs a jar that is already on the cluster, run:
```
gcloud dataproc jobs submit spark --cluster=my-cluster --region=us-central1 --class=org.apache.spark.examples.SparkPi --jars=file:///usr/lib/spark/examples/jars/spark-examples.jar -- 1000
```
[View more ](https://cloud.google.com/sdk/gcloud/reference/dataproc/jobs/submit/spark#--jars)


# [Running the code EX](https://cloud.google.com/dataproc/docs/tutorials/bigquery-connector-spark-example#running_the_code)
