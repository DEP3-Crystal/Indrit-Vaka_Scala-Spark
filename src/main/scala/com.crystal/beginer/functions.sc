
val number = 100;
println(number)
def multiplyBy2(number: Int) = number * 2
println(multiplyBy2(number))

def square(x: Int) = {
  x * x
}
val sq_2 = square(2)
def multiply(nums: Int*) = {
  var product = 1
  for (num <- nums) product = product * num
  product
}
multiply(2, 2, 5)
multiply(2, 2, 5, 200)

//function inside functions
def sumOdd(n: Int) = {
  def getOdd(x: Int): Array[Int] = {
    var result = Array[Int]()
    var current = 1
    while (current <= x) {
      if (current % 2 == 1) result = result :+ current
      current = current + 1
    }
    result
  }

  val odds = getOdd(n)
  println(odds.mkString(","))
  odds.sum
}
sumOdd(10)
