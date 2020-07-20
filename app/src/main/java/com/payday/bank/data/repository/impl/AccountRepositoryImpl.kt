package com.payday.bank.data.repository.impl

import com.payday.bank.data.api.mapper.domain.AccountApiToDomainMapper
import com.payday.bank.data.api.source.base.AccountApiSource
import com.payday.bank.data.repository.base.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val apiSource: AccountApiSource,
    private val apiToDomainMapper: AccountApiToDomainMapper
) : AccountRepository {

    override suspend fun getAccountList() =
        apiSource.getAccountList().map(apiToDomainMapper::map)
}