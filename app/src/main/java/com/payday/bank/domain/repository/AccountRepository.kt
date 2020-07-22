package com.payday.bank.domain.repository

import com.payday.bank.domain.entity.AccountDomainEntity

interface AccountRepository {

    suspend fun getAccountList(customerId: String): List<AccountDomainEntity>
}