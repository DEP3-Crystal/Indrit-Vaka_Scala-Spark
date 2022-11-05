//Type of the function
//        (args) => return type
def square(n: Int): Int = n * n // Int => Int
def cube(n: Int): Int = n * n * n // Int => Int

def transform(f: Int => Int, num: Int*) = num.map(f)


transform(square, 1, 2, 3, 4, 5)
transform(n => n * n * n, 1, 2, 3, 4, 5)


val numbers = Array(1, 2, 3, 4, 5, 6)

numbers.filter(n => n % 2 == 0)
numbers.filter(n => n % 2 == 1)

import Implicits.stringToCurrency

val cad: Currency = "CAD 200"



