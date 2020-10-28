
scalaVersion := "2.11.8"

name := "delight"
organization := "co.datamechanics"
version := "1.0"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.3"

publishTo := sonatypePublishToBundle.value
