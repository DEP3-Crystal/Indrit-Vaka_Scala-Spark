package com.crystal.big_query.ml_100k.model

import org.apache.spark.sql.execution.streaming.FileStreamSource.Timestamp

case class MovieRate(userId: Int, movieId: Int, rating: Byte, timestamp: Timestamp)
