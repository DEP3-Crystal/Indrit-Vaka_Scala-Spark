package com.crystal.methods_functions.functions

object FunctionLiteralsAndPlaceholders extends App {
  val calculateResult = (x: Int, y: Int) => {
    val sum = x + y;
    val diff = x - y
    val product = x * y
    val quotient = x / y
    //returns a tuple
    (sum, diff, product, quotient)
  }

  println("---------- 10 and 5")
  println(calculateResult(10, 5))


  println("---------- 40 and 8")
  println(calculateResult(40, 8))

}
