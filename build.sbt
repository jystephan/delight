
crossScalaVersions := Seq("2.11.12", "2.12.12")

name := "delight"
organization := "co.datamechanics"
version := "1.0-SNAPSHOT"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.3"

publishTo := sonatypePublishToBundle.value
