package CRUD

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.bigquery.{BigQueryOptions, JobId, JobInfo, QueryJobConfiguration}
import com.google.common.collect.ImmutableSet

import java.util.UUID

object Select {
  def main(args: Array[String]): Unit = {
    val credentials = GoogleCredentials.getApplicationDefault.createScoped(ImmutableSet.of("https://www.googleapis.com/auth/bigquery", "https://www.googleapis.com/auth/drive"))
    val bigquery = BigQueryOptions.newBuilder.setCredentials(credentials).setProjectId("bigquery-368715").build.getService


    val queryString = "SELECT * FROM `bigquery-368715.db_crud.person`"

    val queryConfig = QueryJobConfiguration.newBuilder(queryString).setUseLegacySql(false).build

    val jobId = JobId.of(UUID.randomUUID.toString)
    var queryJob = bigquery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build)
    // Wait for the query to complete.
    queryJob = queryJob.waitFor()

    // Check for errors
    if (queryJob == null) throw new RuntimeException("Job no longer exists")
    else if (queryJob.getStatus.getError != null) { // You can also look at queryJob.getStatus().getExecutionErrors() for all
      // errors, not just the latest one.
      throw new RuntimeException(queryJob.getStatus.getError.toString)
    }
    val queryResults = queryJob.getQueryResults()
    import scala.collection.JavaConversions._
    for (row <- queryResults.iterateAll) {
      val id = row.get("id").getStringValue.toInt
      val name = row.get("name").getStringValue
      printf("id: %d, name: %s\n", id, name)
    }
  }
}
