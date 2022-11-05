val anArray = Array(100,200,300)
anArray match {
  case Array(first, second, _) => println("Second=" + second)
}

val aTopple = ("NZD", 100)
println(aTopple._1)
println(aTopple._2)

aTopple match {
  case (currency, amount) if currency =="USD" => print("USD " + amount)
  case (currency, amount) if currency =="NZD" => print("USD " + amount * 1/1.5)
}
