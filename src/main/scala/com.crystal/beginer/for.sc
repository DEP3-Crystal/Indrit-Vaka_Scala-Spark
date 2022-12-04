val amounts = Array(10, 24, 24, 35, 12)
val currencies = Array("USD", "AL")
var sum = 0;
for (amount <- amounts) {
  sum += amount
}
println(sum)

// Expression for loop
val result: Array[Int] = for (amount <- amounts) yield amount
//more than one generator per loop

val result1: Array[String] = for {
  amount <- amounts
  currency <- currencies
  if amount > 20 && amount < 30
} yield currency + " " + amount

//foreach
amounts.foreach(amount => println(amount))
