# Delight

`Delight` is an additional [SparkListener](https://jaceklaskowski.gitbooks.io/mastering-apache-spark/spark-scheduler-SparkListener.html) that captures Spark events and streams them to Delight Collector API.

Features:

- Spark events are bufferized and sent by groups, in order to limit the number of API calls
- payloads are compressed to limit network usage and latency

## Installation


## Build

Build the JAR with

```
sbt package
```

## `Delight` configs
| Config | Default value | Explanation
| :- | :- | :-
| `spark.datamechanics.apiKey.secret` | (none) | An API key to authenticate yourself with Data Mechanics API. If the API key is missing, the listener will not stream events
| `spark.datamechanics.appNameOverride` | `spark.app.name` | The name of the app that will appear in Data Mechanics UI
| `spark.datamechanics.collector.url` | https://ui.datamechanics.co/collector/ | URL of Data Mechanics Collector API
| `spark.datamechanics.buffer.maxNumEvents` | 1000 | The number of Spark events to reach before triggering a call to Data Mechanics Collector API. Special events like job ends also trigger a call.
| `spark.datamechanics.payload.maxNumEvents` | 10000 | The maximum number of Spark events to be sent in one call to Data Mechanics Collector API.
| `spark.datamechanics.heartbeatIntervalSecs` | 10s | (Internal config) the interval at which the listener send an heartbeat requests to the API. It allow us to detect if the app was prematurely finished and start the processing ASAP
| `spark.datamechanics.pollingIntervalSecs` | 0.5s | (Internal config) the interval at which the object responsible for calling the API checks whether there are new payloads to be sent
| `spark.datamechanics.maxPollingIntervalSecs` | 60s | (Internal config) upon connection error, the polling interval increases exponentially until this value. It returns to its initial value once a call to the API passes through
| `spark.datamechanics.maxWaitOnEndSecs` | 10s | (Internal config) the time the Spark application waits for remaining payloads to be sent after the event `SparkListenerApplicationEnd`. Not applicable in the case of Databricks
| `spark.datamechanics.waitForPendingPayloadsSleepIntervalSecs` | 1s | (Internal config) the interval at which the object responsible for calling the API checks whether there are new remaining to be sent, after the event `SparkListenerApplicationEnd` is received. Not applicable in the case of Databricks
