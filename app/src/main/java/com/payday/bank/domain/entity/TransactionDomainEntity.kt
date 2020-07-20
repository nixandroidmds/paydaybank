package com.payday.bank.domain.entity

import java.time.ZonedDateTime

data class TransactionDomainEntity(
    val id: String?,
    val accountId: String?,
    val amount: Double,
    val vendor: String?,
    val category: String?,
    val dateTime: ZonedDateTime?
)