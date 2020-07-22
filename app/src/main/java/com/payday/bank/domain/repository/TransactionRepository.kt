package com.payday.bank.domain.repository

import com.payday.bank.domain.entity.AccountDomainEntity
import com.payday.bank.domain.entity.TransactionDomainEntity

interface TransactionRepository {

    suspend fun getTransactionList(accountList: List<AccountDomainEntity>): List<TransactionDomainEntity>
}