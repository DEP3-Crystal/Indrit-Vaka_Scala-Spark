abstract class Currency

case class USD() extends Currency

case class CAD() extends Currency

case class NZD() extends Currency


val currency: Currency = USD()
val amount = 100

currency match {
  case u: USD => println("USD " + amount)
  case u: CAD => println("CAD " + amount)
  case u: NZD => println("NZD " + amount)
}

