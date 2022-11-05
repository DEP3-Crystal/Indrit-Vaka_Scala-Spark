package com.crystal.methods_functions

object LazyVal extends App{
  var stockPrice = 14
  lazy val volume ={println("Assigning val"); 100}
  println("Calculation final value")
  var finalValue = stockPrice * volume
  println(s"Final value is: $finalValue")
}
