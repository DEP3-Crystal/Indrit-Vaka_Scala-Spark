package com.crystal.classes

import scala.collection.mutable.ArrayBuffer


class Point(val x: Int, val y: Int) {
  val constructor = Point(5, 2)
//  constructor.x = 5;

}


trait Pet {
  val name: String
}

class Cat(val name: String) extends Pet

class Dog(val name: String) extends Pet

val dog = Dog("Harry")
val cat = Cat("Sally")
object StartUp extends App {


  val animals = ArrayBuffer.empty[Pet]
  animals.append(dog)
  animals.addOne(dog)
  animals.append(cat)
  animals.foreach(pet => println(pet.name)) // Prints Harry Sally
}
