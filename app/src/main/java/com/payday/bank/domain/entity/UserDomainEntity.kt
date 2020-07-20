package com.payday.bank.domain.entity

import java.time.ZonedDateTime

data class UserDomainEntity(
    val id: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val gender: String? = null,
    val email: String?,
    val password: String?,
    val zonedDateTime: ZonedDateTime? = null,
    val phone: String? = null
)