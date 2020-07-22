package com.payday.bank.data.repository

import com.payday.bank.data.repository.api.AuthenticatedApiSource
import com.payday.bank.data.repository.impl.api.mapper.domain.AccountApiToDomainMapper
import com.payday.bank.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val apiSource: AuthenticatedApiSource,
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