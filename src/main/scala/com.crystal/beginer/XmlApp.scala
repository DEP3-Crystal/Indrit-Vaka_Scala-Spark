import scala.xml._
object XmlApp extends App {
  private val person: Elem =
    <person>
      <firstName>Indrit</firstName>
      <lastName>Vaka</lastName>
      <emails>
        <email type="primary">indrit.vaka@gmail.com</email>
        <email type="secondary">indrit.vaka@crystal-system.eu</email>
      </emails>
      <address>
        <street>1816sdv sakjdbn dojnksnd</street>
        <city>Tiran</city>
        <zip>1001</zip>
      </address>
    </person>

  //   val seq: NodeSeq = person\ "firstName"
  val seq: NodeSeq = person \\ "email"
  seq.map(email => println(email))

  //xml type attribute
  val seq2 = person \\ "@type"
  print(seq2)
}
