import java.util.UUID

final class Customer(first: String, last: String, email: String) {
  private val _id: UUID = UUID.randomUUID()
  private val _first = first
  private val _last = first
  private val _email = email

  def getId: UUID = _id

  def getFirst: String = _first

  def getLast: String = _last

  def getEmail: String = _email

  override def toString = s"Customer(${_id}, ${_first}, ${_last}, ${_email})"
}

object EmailRunner extends App {
  val c1 = new Customer("Indrit", "Vaka", "indrit@gmail.com")
  println(c1)
}
