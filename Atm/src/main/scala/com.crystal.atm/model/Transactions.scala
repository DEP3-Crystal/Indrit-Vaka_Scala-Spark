package com.crystal.atm.model

import java.time.LocalDateTime

case class Transactions(dateTimeUTC: LocalDateTime, description: String, reference: String, amount: Long)
