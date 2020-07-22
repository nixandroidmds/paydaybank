package com.payday.bank.domain.exception

import java.io.IOException

class NetworkException(
    override val messageForUser: String,
    logMessage: String,
    cause: Throwable? = null
) : IOException(logMessage, cause), MessageForUser