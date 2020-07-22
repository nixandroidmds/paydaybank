package com.payday.bank.domain.entity

import java.time.ZonedDateTime

data class AccountDomainEntity(
    val id: String?,
    val customerId: String?,
    val iban: String?,
    val type: String?,
    val createdDateTime: ZonedDateTime?,
    val active: Boolean
) {

    val anonymousIban: String?
        get() {
            val ibanPartList = iban?.split(" ")
            return if (!ibanPartList.isNullOrEmpty() && ibanPartList.size > 4) {
                val first = ibanPartList.first()
                val last = ibanPartList.last()
                val lastBeforeLast = ibanPartList[ibanPartList.lastIndex - 1]

                "$first **** $lastBeforeLast $last"
            } else {
                null
            }
        }
}