package CRUD

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.bigquery.{BigQueryOptions, QueryJobConfiguration, TableResult}

object Update extends App {

  val credential = GoogleCredentials.getApplicationDefault()
  val bigQuery = BigQueryOptions.newBuilder().setProjectId("bigquery-368715").setCredentials(credential)
    .build().getService
  val updateSql = "UPDATE `bigquery-368715.db_crud.person` SET id = 1 WHERE name='Luka' AND id = 3;"
  val queryConfig = QueryJobConfiguration.newBuilder(updateSql).build()
  val result: TableResult = bigQuery.query(queryConfig)

  printf("%d rows where updated", result.getTotalRows)

}
