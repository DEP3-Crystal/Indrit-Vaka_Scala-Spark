object Currency {
  private val conversionTable: Map[String, Double] = Map("CAD" -> 1 / 1.30, "NZD" -> 1 / 1.50)

  def apply(code: String, amount: Double): Currency = new Currency(code, amount)
  def createUSD(amount:Double) = new Currency("USD",amount)
  def createCAD(amount:Double) = new Currency("CAD",amount)
  def createNZD(amount:Double) = new Currency("NZD",amount)
}

class Currency(code: String, amount: Double) {

  import Currency.conversionTable

  private val _code: String = code
  private val _amount: Double = amount

  private val _inUSD: Double = getInUsd

  def getInUsd: Double = {
    _code match {
      case "USD" => _amount
      case "CAD" => _amount * conversionTable("CAD")
      case "NZD" => _amount * conversionTable("NZD")
      case _ => throw new IllegalAccessException(s"no conversion available for ${_code}")
    }
  }

  override def toString = s"Currency(${_code}, ${_amount}, ${_inUSD})"
}

object CurrencyRunner extends App {
  val usd1 = Currency("USD", 100.12)
  val cad1 = Currency("CAD", 100.12)
  val nzd1 = Currency("NZD", 100.12)
  println(usd1.getInUsd)
  println(cad1.getInUsd)
  println(nzd1.getInUsd)

  import Currency._

  val usd2 =createUSD(100.12)
  val cad2 =createCAD(100.12)
  val nzd2 =createNZD(100.12)
  println(usd2)
  println(cad2)
  println(nzd2)
}

