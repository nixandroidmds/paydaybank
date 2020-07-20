package com.payday.bank.data.repository.base

import com.payday.bank.domain.entity.TransactionDomainEntity

interface TransactionRepository {

    suspend fun getTransactionList(): List<TransactionDomainEntity>
}