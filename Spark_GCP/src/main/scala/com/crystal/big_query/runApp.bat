cd ../../../../../../
sbt clean & sbt package & gcloud auth login & gcloud config set project spark-gcp-367908 & gsutil cp "C:\Users\indri\Desktop\CrystalSystem\Indrit-Vaka_Scala-Spark\Spark_GCP\target\scala-2.12\spark_gcp_2.12-0.1.0-SNAPSHOT.jar" gs://indrit/word_count & gcloud dataproc jobs submit spark --cluster=spark --region=us-central1 --class=com.crystal.big_query.ShakespeareWords --jars=gs://indrit/word_count/spark_gcp_2.12-0.1.0-SNAPSHOT.jar