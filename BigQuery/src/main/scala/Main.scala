

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.bigquery._

import java.io.FileInputStream
import java.util.UUID


object Main {
  def main(args: Array[String]): Unit = {
    // Path of the Service Account key file
    val jsonPath = "C:\\Users\\indri\\Downloads\\bigquery-368715-8d3525b68fc2.json"

    //Set the Service account key as credential
    val credentials = GoogleCredentials.fromStream(new FileInputStream(jsonPath))

    //Initialize BigQuery Client by setting project id and credential
    val bigquery = BigQueryOptions.newBuilder.setProjectId("bigquery-368715").setCredentials(credentials).build.getService

    // Define the query with a QueryJobConfiguration
    val queryConfig = QueryJobConfiguration.newBuilder("SELECT commit, author, repo_name " + "FROM `bigquery-public-data.github_repos.commits` " + "WHERE subject like '%bigquery%' " + "ORDER BY subject DESC LIMIT 10").build

    // Create a job ID so that we can safely retry.
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

    // Get the results.
    val result = queryJob.getQueryResults()

    // Print all pages of the results.
    import scala.collection.JavaConversions._
    for (row <- result.iterateAll) { // String type
      val commit = row.get("commit").getStringValue
      // Record type
      val author = row.get("author").getRecordValue
      val name = author.get("name").getStringValue
      val email = author.get("email").getStringValue
      // String Repeated type
      val repoName = row.get("repo_name").getRecordValue.get(0).getStringValue
      System.out.printf("Repo name: %s Author name: %s email: %s commit: %s\n", repoName, name, email, commit)
    }
  }
}