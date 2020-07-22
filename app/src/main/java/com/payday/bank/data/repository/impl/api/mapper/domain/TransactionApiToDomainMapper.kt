package com.payday.bank.data.repository.impl.api.mapper.domain

import com.payday.bank.data.repository.impl.api.entity.TransactionApiEntity
import com.payday.bank.domain.entity.AccountDomainEntity
import com.payday.bank.domain.entity.TransactionDomainEntity
import javax.inject.Inject

class TransactionApiToDomainMapper @Inject constructor() {

    fun map(transaction: TransactionApiEntity, accountList: List<AccountDomainEntity>) =
        TransactionDomainEntity(
            id = transaction.id,
            account = accountList.firstOrNull { transaction.accountId == it.id },
            amount = transaction.amount ?: 0.0,
            vendor = transaction.vendor,
            category = transaction.category,
            dateTime = transaction.dateTime
        )
}