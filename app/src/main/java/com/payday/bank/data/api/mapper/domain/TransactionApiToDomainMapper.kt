package com.payday.bank.data.api.mapper.domain

import com.payday.bank.data.api.entity.TransactionApiEntity
import com.payday.bank.domain.entity.TransactionDomainEntity
import javax.inject.Inject

class TransactionApiToDomainMapper @Inject constructor() {

    fun map(entity: TransactionApiEntity) =
        TransactionDomainEntity(
            id = entity.id,
            accountId = entity.accountId,
            amount = entity.amount ?: 0.0,
            vendor = entity.vendor,
            category = entity.category,
            dateTime = entity.dateTime
        )
}