package com.crystal.methods_functions

object MethodsFunctions extends App{
  // console codes
  // Dissamble the class
  // cd target...
  // <javap> <className>$.class
  // javap MethodsFunctions$.class
  val data = Array("PS", "MICROSOFT", "GOOGLE", "APPLE", "TESLA")

  def getNumRowsMethod: Int = data.length

  val getNumRowsFunction = () => data.length

  println("Total number of rows (methods):" + getNumRowsMethod)
  println("Total number of rows (function):" + getNumRowsFunction())

  println(getNumRowsMethod.getClass)
  println(getNumRowsFunction.getClass)


  def existsMethod(ticker: String): Boolean = data.contains(ticker)

  val existsFunction = (ticker: String) => data.contains(ticker)


  // Convert method to function

  val exists = existsMethod _


  println("Does PS exist (methods):" + existsMethod("PS"))
  println("Does TM exist (function):" + existsFunction("TM"))

}
