package com.crystal.atm.dao

import com.crystal.atm.model.{Account, Address, Card, User}

import java.time.LocalDate
import scala.collection.mutable

class DataAccessFromMemory extends DataAccess {


  lazy val cards: mutable.Map[String, Card] = loadCards
  lazy val accounts: mutable.Map[String, Account] = loadAccounts
  lazy val users: mutable.Map[Int, User] = loadUsers()

  override def getCards: mutable.Map[String, Card] = cards

  override def getCard(cardNumber: String): Option[Card] = cards.values.find(_.cardNumber.equals(cardNumber))

  override def addCard(card: Card): Unit = cards.addOne(card.cardNumber -> card)

  override def addCards(card: mutable.Map[String, Card]): Unit = cards.addAll(cards)


  override def getAccount(iban: String): Option[Account] = accounts.values.find(_.iban.equals(iban))

  override def getAccounts: mutable.Map[String, Account] = accounts

  override def addAccount(account: Account): Unit = accounts.addOne(account.iban -> account)

  override def addAccounts(accounts: mutable.Map[String, Account]): Unit = accounts.addAll(accounts)


  override def getUsers: mutable.Map[Int, User] = users

  override def getUser(id: Int): Option[User] = users.values.find(_.id.equals(id))

  override def addUsers(users: mutable.Map[Int, User]): Unit = this.users addAll users

  override def addUser(user: User): Unit = users.addOne(user.id, user)


  def loadCards: mutable.Map[String, Card] = {
    val card1 = Card(cardNumber = "1111", iban = "aaa", bin = "AL1234BB", cvv = "115", cardType = "Debit", pin = "3345")
    val card2 = Card(cardNumber = "2222", iban = "bbb", bin = "AL1234BB", cvv = "115", cardType = "Debit", pin = "3345")
    mutable.Map(card1.cardNumber -> card1, card2.cardNumber -> card2)
  }

  def loadAccounts: mutable.Map[String, Account] = {
    val iban = "aaa";
    val acc1 = Account(iban, userId = 1, cards.filter(_._2.iban.equals(iban)), "Al")
    val iban2 = "bbb";
    val acc2 = Account(iban2, userId = 2, cards.filter(_._2.iban.equals(iban2)), "Al")

    mutable.Map(acc1.iban -> acc1, acc2.iban -> acc2)
  }

  def loadUsers(): mutable.Map[Int, User] = {
    val indritId = 1
    val lukaId = 2
    val indritAccounts = accounts.filter(_._2.userId.equals(indritId))
    val indritAddress = Address(indritId, "Tirane, albania", "Tirane", "Albania", "Albania", "1001")
    val indrit = User(indritId, "Indrit", "Vaka", LocalDate.of(2001, 1, 1), "+355676923049", "indrit.vaka@gmail.com", indritAddress, indritAccounts)

    val lukaAccounts = accounts.filter(_._2.userId.equals(lukaId))

    val lukaAddress = Address(lukaId, "Tirane, albania", "Tirane", "Albania", "Albania", "1001")
    val luka = User(lukaId, "Luka", "Buziu", LocalDate.of(2001, 1, 1), "+355676925947", "luka.buziu@gmail.com", lukaAddress, lukaAccounts)

    mutable.Map(indrit.id -> indrit, luka.id -> luka)
  }

}
