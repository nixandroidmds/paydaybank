package com.payday.bank.domain.exception

open class MessageException(
    override val messageForUser: String,
    logMessage: String? = null,
    cause: Throwable? = null
) : Exception(logMessage ?: messageForUser, cause), MessageForUser