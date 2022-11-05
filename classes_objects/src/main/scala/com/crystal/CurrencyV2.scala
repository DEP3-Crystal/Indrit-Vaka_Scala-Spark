object CurrencyV2 {
  private val conversionTable: Map[String, Double] = Map("CAD" -> 1 / 1.30, "NZD" -> 1 / 1.50)

  def apply(code: String, amount: Double): CurrencyV2 = new CurrencyV2(code, amount,getInUsd("USD"  ,amount))
  def createUSD(amount:Double): CurrencyV2 =  CurrencyV2("USD",amount)
  def createCAD(amount:Double): CurrencyV2 =  CurrencyV2("CAD",amount)
  def createNZD(amount:Double): CurrencyV2 =  CurrencyV2("NZD",amount)

  def getInUsd(code:String, amount:Double): Double = {
    code match {
      case "USD" => amount
      case "CAD" => amount * conversionTable("CAD")
      case "NZD" => amount * conversionTable("NZD")
      case _ => throw new IllegalAccessException(s"no conversion available for ${code}")
    }
  }
}

class CurrencyV2(code: String, amount: Double, inUSD:Double) {

  import CurrencyV2.conversionTable

  private val _code: String = code
  private val _amount: Double = amount

  private val _inUSD: Double = inUSD



  override def toString = s"CurrencyV2(${_code}, ${_amount}, ${_inUSD})"
}

object CurrencyRunnerV2 extends App {
  val usd1 = CurrencyV2("USD", 100.12)
  val cad1 = CurrencyV2("CAD", 100.12)
  val nzd1 = CurrencyV2("NZD", 100.12)
  println(usd1)
  println(cad1)
  println(nzd1)

  import CurrencyV2._

  val usd2 =createUSD(100.12)
  val cad2 =createCAD(100.12)
  val nzd2 =createNZD(100.12)
  println(usd2)
  println(cad2)
  println(nzd2)
}

