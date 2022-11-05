object Main {
  def main(args: Array[String]): Unit = {
    // money "USD 100"
    val Array(code: String, value: String) = args(0).split("\\s")
    val valueAsDouble: Double = value.toDouble
    if (!Set("CAD", "NZD").contains(code)) {
      println("Supported currency are CAD, and NZD")
      sys.exit()
    }
    val rate: Double = Utils.getRateFrom(code)
    print(args(0) + " = USD " + (rate * valueAsDouble))

  }

}
object Utils {
 private val cadToUsd: Double = 1/1.30
  private val nzdToUsd: Double = 1/1.50
  def getRateFrom(code: String): Double = {
    println("getting rate from: " + code)
    code match {
      case "NZD" => nzdToUsd
      case "CAD" => cadToUsd
    }
  }
}