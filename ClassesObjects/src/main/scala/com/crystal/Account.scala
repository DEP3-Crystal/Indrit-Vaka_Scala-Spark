import java.time.LocalDateTime
import java.util.UUID

abstract class Account(id: UUID, name: String, dateOpened: LocalDateTime) {

  protected val _accountType: String

  def getAccountType: String = _accountType

  private var _id: UUID = id
  private var _name: String = name
  private var _dateOpened: LocalDateTime = dateOpened

  //Auxiliary constructor
  def this(name: String) {
    this(UUID.randomUUID(), name, LocalDateTime.now())
  }

  def getId: UUID = _id;

  def getName: String = _name;

  def getDateOpened: LocalDateTime = _dateOpened

  def setName(newName: String): Unit = _name = newName

  override def toString: String = s"id=${_id},name=${_name},openedData${_dateOpened}"
}

class CreditAccount(name: String) extends Account(name: String) {
  override val _accountType = "Credit"

}

class DepositAccount(name: String) extends Account(name: String) {
  override val _accountType: String = "Deposit"
}

object AccountRunner extends App {
  //  val a1 = new Account(UUID.randomUUID(), "Account 01", LocalDateTime.now())
  //  val a2 = new Account(UUID.randomUUID(), "Account 02", LocalDateTime.now().plusHours(6))
  //  val a3 = new Account("Account 03")
  //  a1.setName("Updated account 01")
  //  println(a1.getId, a1.getName, a1.getDateOpened)
  //  println(a1)
  //  println(a2.getId, a2.getName, a2.getDateOpened)
  //  println(a3.getId, a3.getName, a3.getDateOpened)

  val ca1: Account = new CreditAccount("Account 01")
  println(ca1.getId, ca1.getName, ca1.getDateOpened, ca1.getAccountType)
  println(ca1)
}