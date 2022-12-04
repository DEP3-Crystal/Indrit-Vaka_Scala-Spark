package com.crystal.methods_functions.methods

object Calculator extends App {
  def computeSum(a: Int, b: Int): Int = {
    a + b
  }

  def printSum(a: Int, b: Int): Unit = {
    println(s"Sum of a and b is: ${a + b}")
  }

  val result = computeSum(5, 6)
  println("Result is: " + result)
  printSum(11, 12)
}
