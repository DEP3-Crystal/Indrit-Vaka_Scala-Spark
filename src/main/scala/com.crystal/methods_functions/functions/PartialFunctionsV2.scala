package com.crystal.methods_functions.functions

object PartialFunctionsV2 extends App {
  //  val divide64By = (x: Int) => 64 / x
  //  println(divide64By(8))
  // println(divide64By(0))

  // a partial function which doesn't accepts 0 as args
  val divide64By: PartialFunction[Int, Int] = {
    case x: Int if x != 0 => 64 / x
  }

  println("Defined at 11: " + divide64By.isDefinedAt(11))
  println("Defined at 0: " + divide64By.isDefinedAt(0))
  println("Divide 64 by 3: " + (if (divide64By.isDefinedAt(3)) divide64By(3) else "not defined at 3"))
}
