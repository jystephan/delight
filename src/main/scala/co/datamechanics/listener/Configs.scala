package co.datamechanics.listener

import org.apache.spark.SparkConf

import scala.concurrent.duration._

object Configs {

  def collectorUrl(sparkConf: SparkConf): String = {
    sparkConf.get("spark.datamechanics.collector.url", "https://ui.datamechanics.co/collector/")
  }

  def bufferMaxSize(sparkConf: SparkConf): Int = {
    sparkConf.getInt("spark.datamechanics.buffer.maxNumEvents", 1000)
  }

  def payloadMaxSize(sparkConf: SparkConf): Int = {
    sparkConf.getInt("spark.datamechanics.payload.maxNumEvents", 10000)
  }

  def apiKeyOption(sparkConf: SparkConf): Option[String] = {
    sparkConf.getOption("spark.datamechanics.apiKey.secret") // secret is added here so that Spark redacts this config
  }

  def heartbeatInterval(sparkConf: SparkConf): FiniteDuration = {
    sparkConf.getDouble("spark.datamechanics.heartbeatIntervalSecs", 10).seconds
  }

  def pollingInterval(sparkConf: SparkConf): FiniteDuration = {
    sparkConf.getDouble("spark.datamechanics.pollingIntervalSecs", 0.5).seconds
  }

  def maxPollingInterval(sparkConf: SparkConf): FiniteDuration = {
    sparkConf.getDouble("spark.datamechanics.maxPollingIntervalSecs", 60).seconds
  }

  def maxWaitOnEnd(sparkConf: SparkConf): FiniteDuration = {
    sparkConf.getDouble("spark.datamechanics.maxWaitOnEndSecs", 10).seconds
  }

  def waitForPendingPayloadsSleepInterval(sparkConf: SparkConf): FiniteDuration = {
    sparkConf.getDouble("spark.datamechanics.waitForPendingPayloadsSleepIntervalSecs", 1).seconds
  }

  def generateDMAppId(sparkConf: SparkConf): String = {
    val appName: String = sparkConf.get("spark.datamechanics.appNameOverride", sparkConf.get("spark.app.name", "undefined")).replace(" ", "-")
    val uuid: String = java.util.UUID.randomUUID().toString
    s"$appName-$uuid"
  }

}
