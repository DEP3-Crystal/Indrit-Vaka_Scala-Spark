package com.crystal.atm

import com.crystal.atm.dao.{DataAccess, DataAccessFromCsv, DataAccessFromMemory}
import com.crystal.atm.model.User
import com.crystal.atm.services.DataAccessService

import java.time.LocalDate
import scala.collection.mutable

object DataAccessFromMemoryTest extends App {
  def fromMemory(): Unit = {

    val dataAccess: DataAccess = new DataAccessFromMemory
    val dataAccessService = new DataAccessService(dataAccess)
    dataAccessService.addUser(User(3, "ascsa", "asaasc", LocalDate.now, "asca", "sadcdas", null, mutable.Map.empty))
    dataAccessService.getUsers.foreach(println)
    dataAccessService.getUsers.foreach(println)
  }

  fromCsv

  def fromCsv: Unit = {
    val dataAccess: DataAccess = new DataAccessFromCsv
    val dataAccessService = new DataAccessService(dataAccess)
    dataAccessService.getCards.foreach(println)
  }


}
