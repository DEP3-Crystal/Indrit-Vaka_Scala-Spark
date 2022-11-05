package com.crystal.atm.model

import java.time.LocalDate
import scala.collection.mutable;

case class User(id:Int, firstName: String, lastName:String, birthDay:LocalDate, phoneNumber:String, email:String, address :Address,accounts:mutable.Map[String, Account])
