package com.crystal.methods_functions

object Val extends App {

  val data = {
    println("Array is initialized")
    Array("MICROSOFT", "GOOGLE", "TM")
  }

  println("Before accessing data")
  data.foreach(println)
  println("--------------------------")
  //  data = {println("new array is initialized"); Array("APPLE")}
  data(0) = "APPLE"
  data(2) = "TESLA"
  data.foreach(println)

}
