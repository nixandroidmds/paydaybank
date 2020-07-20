package com.payday.bank.domain.exception

class NetworkException(
    message: String,
    logMessage: String,
    cause: Throwable? = null
) : MessageException(
    message = message,
    logMessage = logMessage,
    cause = cause
)