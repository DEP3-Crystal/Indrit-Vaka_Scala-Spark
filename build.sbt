ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.0"

lazy val root = (project in file("."))
  .settings(
    name := "Indrit-Vaka_Scala-Spark"
  )
libraryDependencies+= "org.scala-lang.modules" %% "scala-xml"%"2.1.0"
