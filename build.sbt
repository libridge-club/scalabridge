ThisBuild / scalaVersion := "3.5.0"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "club.libridge"
ThisBuild / organizationName := "libridge"

lazy val root = (project in file("."))
  .settings(
    name := "scalabridge",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % "test",
    libraryDependencies += "org.scalatestplus" %% "junit-5-10" % "3.2.19.0" % "test",
    Test / logBuffered := false,
    scalacOptions ++= Seq(
      "-deprecation", // emit warning and location for usages of deprecated APIs
      "-encoding",
      "utf-8", // Specify character encoding used by source files.
      "-explain", // explain errors in more detail
      "-explain-cyclic", // explain cyclic errors in more detail
      "-explain-types", // explain type errors in more detail
      "-feature", // emit warning and location for usages of features that should be imported explicitly
      "-print-lines", // show source code line numbers.
      "-unchecked", // enable additional warnings where generated code depends on assumptions
      "-Xfatal-warnings", // fail the compilation if there are any warnings
      "-Wconf:any:verbose", // shows warning categories
      "-Wconf:id=E176&msg=org.scalatest.*Assertion:s", // Disable Wnonunit-statement warnings related to ScalaTest Assertion.
      "-Wnonunit-statement",
      "-Wunused:imports",
      "-Wunused:privates",
      "-Wunused:locals",
      "-Wunused:params",
      "-Wvalue-discard"
    )
  )
