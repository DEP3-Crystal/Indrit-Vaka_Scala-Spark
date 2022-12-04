package com.crystal.atm.dao

import com.crystal.atm.model.{Account, Card, User}

import scala.collection.mutable

trait DataAccess {
  val cards: scala.collection.mutable.Map[String, Card]
  val accounts: scala.collection.mutable.Map[String, Account]
  val users: scala.collection.mutable.Map[Int, User]

  def getUsers: mutable.Map[Int, User]

  def getUser(id: Int): Option[User]

  def addUser(user: User): Unit

  def addUsers(people: mutable.Map[Int, User]): Unit


  def getCard(cardId: String): Option[Card]

  def getCards: mutable.Map[String, Card]

  def addCard(card: Card): Unit

  def addCards(cards: mutable.Map[String, Card]): Unit


  def getAccount(iban: String): Option[Account]

  def getAccounts: mutable.Map[String, Account]

  def addAccounts(account: mutable.Map[String, Account]): Unit

  def addAccount(account: Account): Unit

}
