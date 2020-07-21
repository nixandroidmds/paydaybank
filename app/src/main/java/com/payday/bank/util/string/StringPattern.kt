package com.payday.bank.util.string

object StringPattern {
    val PASSWORD = "^([a-zA-Z]+[0-9]+([a-zA-Z0-9]+)?|[0-9]+[a-zA-Z]+([a-zA-Z0-9]+)?)$".toRegex()
}