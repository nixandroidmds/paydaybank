package com.payday.bank.domain.entity

import java.time.ZonedDateTime

data class AccountDomainEntity(
    val id: String?,
    val customerId: String?,
    val iban: String?,
    val type: String?,
    val createdDateTime: ZonedDateTime?,
    val active: Boolean
)