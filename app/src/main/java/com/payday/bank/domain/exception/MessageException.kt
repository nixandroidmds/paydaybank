package com.payday.bank.domain.exception

open class MessageException(
    message: String? = null,
    logMessage: String? = null,
    cause: Throwable? = null
) : Exception(logMessage ?: message, cause) {

    open fun getMessageForUser() = message
}