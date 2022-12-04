package com.crystal.methods_functions

object Var extends App {

  var data = {
    println("Array is initialized")
    Array("MICROSOFT", "GOOGLE", "TM")
  }

  println("Before accessing data")
  data.foreach(println)
  println("--------------------------")
  data = {
    println("new array is initialized"); Array("APPLE")
  }
  data.foreach(println)

}
