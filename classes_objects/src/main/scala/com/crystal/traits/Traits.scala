package com.crystal.traits

import java.util.UUID

trait Address {
  protected var _zip: Int
  protected var _street: String
  protected var _state: String

  def setZip(zip: Int): Unit

  def setStreet(street: String): Unit

  def setState(state: String): Unit
}

class BaseCustomer(first: String, last: String, email: String) {
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

class Costumer(firstName: String, lastName: String, email: String) extends BaseCustomer(firstName, lastName, email) with Address {

  override protected var _zip: Int = -1
  override protected var _street: String = ""
  override protected var _state: String = ""

  override def setZip(zip: Int): Unit = _zip = zip

  override def setStreet(street: String): Unit = _street = street

  override def setState(state: String): Unit = _state = state
}