package com.crystal.kv_paris

import org.apache.spark.{SparkConf, SparkContext}

object Main extends App {

  val conf = new SparkConf()
    .setAppName("Car_Stats")
    .setMaster("local[*]") //* is number of cores

  val root = "C:\\Users\\indri\\Desktop\\CrystalSystem\\Indrit-Vaka_Scala-Spark\\source\\car_ads"
  val sc = SparkContext.getOrCreate(conf)
  val header = "car,price,body,mileage,engV,engType,registration,year,model,drive"

  sc.textFile(root + "/car_ads_*.csv")

    .filter(!_.contains(header))
    .filter(_.split(",").length >= 10)
    .map(DeserializeCar.deserialize)
    .map(car=>new ToKVByEntry[String, Car](key=>key.car).convert(car))
    .groupByKey()
    .map(kv=> {
      val key = kv._1
      val avg = kv._2.map(_.price).sum / kv._2.size
      (key, avg)
    })
    .saveAsTextFile(root+"sink/car_ads")
//    .foreach(println)


}

object DeserializeCar {
  def deserialize: String => Car = line => {
    val entries = line.split(",")
    //car,price,body,mileage,engV,engType,registration,year,model,drive
    Car(entries(0), entries(1).toDouble, entries(2), entries(3), entries(4), entries(5),
      entries(6), entries(7).toInt, entries(8), entries(9))
  }
}

class ToKVByEntry[K, V](keyProvider: KeyProvider[K,V]) {

  def convert(value: V): (K, V) = {
    (keyProvider.getKey(value), value)
  }

}

trait KeyProvider[K, V] {
  def getKey(v: V): K
}
