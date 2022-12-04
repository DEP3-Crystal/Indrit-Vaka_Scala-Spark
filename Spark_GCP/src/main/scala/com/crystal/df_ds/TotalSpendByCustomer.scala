package com.crystal.df_ds

import com.crystal.rdd.Path
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.functions.{col, lit, round, sum}
import org.apache.spark.sql.types._
import org.apache.spark.sql.{SparkSession, functions}

object TotalSpendByCustomer {
  case class Order(customerId: Int, productId: Int, cost: Double)

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)

    val spark = SparkSession.builder()
      .appName("Customer spend")
      .master("local[*]")
      .getOrCreate()

    val orderSchema = new StructType()
      .add("customerId", IntegerType, nullable = true)
      .add("productId", IntegerType, nullable = true)
      .add("cost", DoubleType, nullable = true)
    import spark.implicits._
    val ordersDS = spark.read
      .schema(orderSchema)
      .csv(Path.u_dataPath + "customer-orders.csv")
      .as[Order]

    ordersDS.groupBy("customerId")
      .agg(functions.concat(round(sum("cost"), 2), lit(" $").alias("total_spends")))
      //      .sum("cost")
      //      .select(col("customerId"), col("total_spends").alias("total_spends"))
      .sort(col("total_spends").desc)
      .show()
  }

}
