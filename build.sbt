ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.3"

import Dependencies._

lazy val root = (project in file("."))
  .settings(
    name := "ayss",
    idePackagePrefix := Some("com.github.mercurievv"),
    libraryDependencies ++= Seq(Cats.effect, Cats.core, Streams.fs2Core)
  )
