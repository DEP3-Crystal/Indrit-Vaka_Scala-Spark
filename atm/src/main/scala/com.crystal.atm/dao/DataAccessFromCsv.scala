package com.crystal.atm.dao

import com.crystal.atm.model.{Account, Card, User}

import scala.collection.mutable

class DataAccessFromCsv extends DataAccess {

  val rootPath = "source";
  val cardsPath = rootPath concat("/cards.csv")
  val accountsPath = rootPath concat("/accounts.csv")
  val userPath = rootPath concat("/users.csv")

  override val cards: mutable.Map[String, Card] = loadCards



 lazy override val accounts: mutable.Map[String, Account] = ???
 lazy override val users: mutable.Map[Int, User] = ???



  override def getUsers: mutable.Map[Int, User] = ???

  override def getUser(id: Int): Option[User] = ???

  override def addUser(user: User): Unit = ???

  override def addUsers(people: mutable.Map[Int, User]): Unit = ???

  override def getCard(cardId: String): Option[Card] = ???

  override def getCards: mutable.Map[String, Card] = cards

  override def addCard(card: Card): Unit = ???

  override def addCards(cards: mutable.Map[String, Card]): Unit = ???

  override def getAccount(iban: String): Option[Account] = ???

  override def getAccounts: mutable.Map[String, Account] = ???

  override def addAccounts(account: mutable.Map[String, Account]): Unit = ???

  override def addAccount(account: Account): Unit = ???

  def loadCards: mutable.Map[String, Card] = {
    val cardsBuffer = io.Source.fromFile(cardsPath)
    val lines = cardsBuffer.getLines()
    lines.map(line=> {
      val entries = line.split(",").map(_.trim)
      //cardNumber,iban,bin,cvv,cardType,pin
      val cardNumber = entries(0)
      val iban = entries(1)
      val bin = entries(2)
      val cvv = entries(3)
      val cardType = entries(4)
      val pin = entries(5)
     cardNumber-> Card(cardNumber,iban,bin, cvv, cardType, pin)
    }) to mutable.Map
  }



}
