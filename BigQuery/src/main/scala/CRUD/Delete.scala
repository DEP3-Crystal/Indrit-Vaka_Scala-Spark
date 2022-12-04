package CRUD

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.bigquery._

object Delete {
  def main(args: Array[String]): Unit = {
    val credentials = GoogleCredentials.getApplicationDefault
    val bigQuery = BigQueryOptions.newBuilder.setCredentials(credentials).setProjectId("bigquery-368715").build.getService

    val deleteQuery = "DELETE FROM `bigquery-368715.db_crud.person` WHERE id = 1"
    val queryConfig = QueryJobConfiguration.newBuilder(deleteQuery).build

    val queryJob = bigQuery.query(queryConfig)

    println("Deleted rows: " + queryJob.getTotalRows)
  }
}
