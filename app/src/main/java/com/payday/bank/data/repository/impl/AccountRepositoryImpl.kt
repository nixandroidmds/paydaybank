package com.payday.bank.data.repository.impl

import com.payday.bank.data.api.mapper.domain.AccountApiToDomainMapper
import com.payday.bank.data.api.source.base.AccountApiSource
import com.payday.bank.data.repository.base.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val apiSource: AccountApiSource,
    private val apiToDomainMapper: AccountApiToDomainMapper
) : AccountRepository {

    override suspend fun getAccountList(customerId: String) =
        apiSource
            .getAccountList()
            .asSequence()
            .filter { it.customerId == customerId }
            .map(apiToDomainMapper::map)
            .toList()
}