package com.crystal.atm.services

import com.crystal.atm.dao.DataAccess
import com.crystal.atm.model.{Account, Address, Card, User}

import java.time.LocalDate
import scala.collection.mutable

class DataAccessService(dataAccess: DataAccess) {


  def getCards: mutable.Map[String, Card] = dataAccess.getCards

  def getCard(cardNumber: String): Option[Card] = dataAccess.getCard(cardNumber)

  def addCard(card: Card): Unit = dataAccess.addCard(card)
  def addCards(cards: mutable.Map[String,Card]): Unit = dataAccess.addCards(cards)


  def getAccount(iban: String): Option[Account] = dataAccess.getAccount(iban)

  def getAccounts: mutable.Map[String, Account] = dataAccess.getAccounts

  def addAccount(account: Account): Unit = {
    dataAccess.addAccount(account)
    account.cards.foreach(card => dataAccess.addCard(card._2))
  }
  def addAccounts(accounts: mutable.Map[String,Account]): Unit = {
    dataAccess.addAccounts(accounts)
    accounts.foreach(acc=>addCards(acc._2.cards))
  }


  def getUsers: mutable.Map[Int, User] = dataAccess.getUsers

  def getUser(id: Int): Option[User] = dataAccess.getUser(id)

  def addUsers(users: scala.collection.mutable.Map[Int, User]): Unit = {
    users.foreach(user=>addAccounts(user._2.accounts))
    dataAccess.addUsers(users)
  }

  def addUser(user: User): Unit = {
    user.accounts.foreach(account => addAccount(account._2))
    dataAccess.addUser(user)
  }



}
