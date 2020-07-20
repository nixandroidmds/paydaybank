package com.payday.bank.data.repository.base

import com.payday.bank.domain.entity.AccountDomainEntity

interface AccountRepository {

    suspend fun getAccountList(): List<AccountDomainEntity>
}