# Delight by Data Mechanics

[Delight](https://www.datamechanics.co/delight) is a replacement for the Spark UI to help debug Spark performance.
Read this [blog post](https://www.datamechanics.co/blog-post/building-a-better-spark-ui-data-mechanics-delight) to learn more about it.

While the blog post above details our vision, the project is still under active development.
In the meantime Delight is a free Spark History Server.

Why? We know that many Spark users out there don't have a ready-to-use Spark History Server, because either their platform does not offer one or it is not made available to them.
Yet a Spark History Server (short of a better alternative 🙂) is essential to understand the performance of Spark applications.

That's why we wrote a [SparkListener](https://jaceklaskowski.gitbooks.io/mastering-apache-spark/spark-scheduler-SparkListener.html) that is dead simple to install.
It streams Spark events logs to our servers, and we host a Spark History Server for you, free of charge!

## Installation

To install Delight on your Spark application,

- go to [Delight website](https://www.datamechanics.co/delight), create an account and generate an API key,
- follow the installation steps for your platform.

Here are the available instructions:
- [hello world: local run with the `spark-submit` CLI](documentation/local_run.md)
- [generic instructions for the `spark-submit` CLI](documentation/spark_submit.md)
- [AWS EMR](documentation/aws_emr.md)
- Databricks (TODO)

Let us know if you'd like instructions for other platforms!

## Data Mechanics Delight configurations

| Config                          | Default value    | Explanation                                                                                                                                                                     |
| :------------------------------ | :--------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `spark.delight.apiKey.secret`   | (none)           | An API key to authenticate yourself with Data Mechanics Delight. If the API key is missing, the listener will not stream events                                                 |
| `spark.delight.appNameOverride` | `spark.app.name` | The name of the app that will appear in Data Mechanics Delight. This is only useful if your platform does not allow you to set `spark.app.name` (ex: Databricks (TODO verify)). |

### Advanced configurations

We've listed more technical configurations in this section for completeness.
You should not need to change the values of these configurations though, so drop us a line if you do, we'll be interested to know more!

| Config                                                  | Default value                                   | Explanation                                                                                                                                                                                                                               |
| :------------------------------------------------------ | :---------------------------------------------- | :---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `spark.delight.collector.url`                           | https://api.delight.datamechanics.co/collector/ | URL of the Data Mechanics Delight collector API                                                                                                                                                                                           |
| `spark.delight.buffer.maxNumEvents`                     | 1000                                            | The number of Spark events to reach before triggering a call to Data Mechanics Collector API. Special events like job ends also trigger a call.                                                                                           |
| `spark.delight.payload.maxNumEvents`                    | 10000                                           | The maximum number of Spark events to be sent in one call to Data Mechanics Collector API.                                                                                                                                                |
| `spark.delight.heartbeatIntervalSecs`                   | 10s                                             | (Internal config) the interval at which the listener send an heartbeat requests to the API. It allow us to detect if the app was prematurely finished and start the processing ASAP                                                       |
| `spark.delight.pollingIntervalSecs`                     | 0.5s                                            | (Internal config) the interval at which the object responsible for calling the API checks whether there are new payloads to be sent                                                                                                       |
| `spark.delight.maxPollingIntervalSecs`                  | 60s                                             | (Internal config) upon connection error, the polling interval increases exponentially until this value. It returns to its initial value once a call to the API passes through                                                             |
| `spark.delight.maxWaitOnEndSecs`                        | 10s                                             | (Internal config) the time the Spark application waits for remaining payloads to be sent after the event `SparkListenerApplicationEnd`. Not applicable in the case of Databricks                                                          |
| `spark.delight.waitForPendingPayloadsSleepIntervalSecs` | 1s                                              | (Internal config) the interval at which the object responsible for calling the API checks whether there are new remaining to be sent, after the event `SparkListenerApplicationEnd` is received. Not applicable in the case of Databricks |
