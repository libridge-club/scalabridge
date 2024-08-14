ThisBuild / scalaVersion := "3.5.0"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "club.libridge"
ThisBuild / organizationName := "libridge"

lazy val root = (project in file("."))
  .settings(
    name := "scalabridge",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % "test",
    libraryDependencies += "org.scalatestplus" %% "junit-5-10" % "3.2.19.0" % "test"
  )
