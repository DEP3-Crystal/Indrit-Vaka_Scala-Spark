package CRUD

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.bigquery.{BigQueryOptions, QueryJobConfiguration}

object Insert {
  def main(args: Array[String]): Unit = {
    val credentials = GoogleCredentials.getApplicationDefault()

    val bigQuery = BigQueryOptions.newBuilder.setProjectId("bigquery-368715").setCredentials(credentials).build.getService
    val Insert_Person = getInsertSQL(new Person(1, "Indrit"), new Person(2, "Luka"))
    System.out.println(Insert_Person)

    val queryConfig = QueryJobConfiguration.newBuilder(Insert_Person).build
    val queryJob = bigQuery.query(queryConfig)

    printf("%d rows inserted", queryJob.getTotalRows)


  }

  private def getInsertSQL(people: Person*): String = {
    val peopleString = people.map((person: Person)
    => "(" + person.id + ",'" + person.name + "')").mkString(",")

    "INSERT INTO `bigquery-368715.db_crud.person` (id,name) VALUES " + peopleString + ";"
  }
}
