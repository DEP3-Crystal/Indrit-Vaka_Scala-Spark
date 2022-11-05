package com.crystal.atm

import scala.io.BufferedSource

object FileTest extends App{
  val rootPath = "source"
  val personPath = rootPath concat "/people.json"
  val file: BufferedSource = io.Source.fromFile(personPath)
}
