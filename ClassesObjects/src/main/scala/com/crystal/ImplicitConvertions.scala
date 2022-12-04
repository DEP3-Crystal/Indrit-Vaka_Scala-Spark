object CurrencyV3 {
  private val conversionTable: Map[String, Double] = Map("CAD" -> 1 / 1.30, "NZD" -> 1 / 1.50)

  def apply(code: String, amount: Double): CurrencyV3 = new CurrencyV3(code, amount, getInUsd("USD", amount))

  def createUSD(amount: Double): CurrencyV3 = CurrencyV3("USD", amount)

  def createCAD(amount: Double): CurrencyV3 = CurrencyV3("CAD", amount)

  def createNZD(amount: Double): CurrencyV3 = CurrencyV3("NZD", amount)

  def getInUsd(code: String, amount: Double): Double = {
    code match {
      case "USD" => amount
      case "CAD" => amount * conversionTable("CAD")
      case "NZD" => amount * conversionTable("NZD")
      case _ => throw new IllegalAccessException(s"no conversion available for ${code}")
    }
  }

  implicit def stringToCurrency(money: String): CurrencyV3 = {
    val Array(code: String, value: String) = money.split(" ")
    val requestedAmount: Double = value.toDouble
    CurrencyV3(code, requestedAmount)
  }
}

class CurrencyV3(code: String, amount: Double, inUSD: Double) {

  private val _code: String = code
  private val _amount: Double = amount

  private val _inUSD: Double = inUSD

  override def toString = s"CurrencyV3(${_code}, ${_amount}, ${_inUSD})"
}

object CurrencyRunnerV3 extends App {
  val usd1 = CurrencyV3("USD", 100.12)
  val cad1 = CurrencyV3("CAD", 100.12)
  val nzd1 = CurrencyV3("NZD", 100.12)
  println(usd1)
  println(cad1)
  println(nzd1)

  import CurrencyV3._

  val usd2 = createUSD(100.12)
  val cad2 = createCAD(100.12)
  val nzd2 = createNZD(100.12)
  println(usd2)
  println(cad2)
  println(nzd2)

  val nzd3: CurrencyV3 = "NZD 25"
  println(nzd3)
}

