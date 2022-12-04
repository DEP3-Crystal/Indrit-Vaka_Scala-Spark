package com.crystal.atm.model

import scala.collection.mutable

case class Account(iban: String, userId: Int,  cards: mutable.Map[String, Card], currencyType: String, balance: Long = 0,transactions: List[Transactions] =  List.empty)
