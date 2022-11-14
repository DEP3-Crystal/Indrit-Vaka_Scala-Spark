package com.crystal.gcp

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.udf

object SparkCurryFunctionUDS {
  // spark-submit --master yarn --deploy-mode client --class com.crystal.gcp.SparkCurryFunctionUDS spark_gcp_2.12-0.1.0-SNAPSHOT.jar <args separated with space>
  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder()
      .appName("Spark UDF Using curry functions")
      .master("local[*]")
      .getOrCreate()

    spark.sparkContext.setLogLevel("WARN")

    val header = "SYMBOL,SERIES,OPEN,HIGH,LOW,CLOSE,LAST,PREVCLOSE,TOTTRDQTY,TOTTRDVAL,TIMESTAMP,TOTALTRADES,ISIN"
    val path = "C:\\Users\\indri\\Desktop\\CrystalSystem\\datasets-master\\nse-stocks\\nse-stocks-data.csv"
    val data = spark.read
      .option("inferSchema", "true")
      .option("header", "true")
      .csv(path)
    //      .csv(args(0))
    data.show()
    //    val precision = args(1).toInt
    val precision = 5
    val diffData = data.withColumn("DIFF_CLOSE", diffCalculation(precision)(data("CLOSE"), data("PREVCLOSE")))
    diffData.show()
  }

  def diffCalculation(precision: Int): UserDefinedFunction = udf((close: Double, prevClose: Double) => {
    BigDecimal(close - prevClose).setScale(precision, BigDecimal.RoundingMode.HALF_UP).toDouble
    //    close - prevClose
  })

}
