package com.crystal.methods_functions.methods

object GenericTypeParam extends App {
  val result: String = pickRandom(List("Google", "Microsoft", "Apple", "Tesla", "Samsung"))

  def pickRandom[A](list: Seq[A]): A = {
    val randomNumber: Int = util.Random.nextInt(list.length)
    list(randomNumber)
  }

  println(result)
}
