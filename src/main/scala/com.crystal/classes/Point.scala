package com.crystal.classes

import scala.collection.mutable.ArrayBuffer


class Point(val x: Int, val y: Int) {
  val constructor = new Point(5, 2)
  //  constructor.x = 5;

}


trait Pet {
  val name: String
}

class Cat(val name: String) extends Pet

class Dog(val name: String) extends Pet


object StartUp extends App {
  val dog = new Dog("Harry")
  val cat = new Cat("Sally")

  val animals = ArrayBuffer.empty[Pet]
  animals.append(dog)
  animals.addOne(dog)
  animals.append(cat)
  animals.foreach(pet => println(pet.name)) // Prints Harry Sally
}
