package com.payday.bank.data.repository.impl

import com.payday.bank.data.api.mapper.domain.TransactionApiToDomainMapper
import com.payday.bank.data.api.source.base.TransactionApiSource
import com.payday.bank.data.repository.base.TransactionRepository
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val apiSource: TransactionApiSource,
    private val apiToDomainMapper: TransactionApiToDomainMapper
) : TransactionRepository {

    override suspend fun getTransactionList() =
        apiSource.getTransactionList().map(apiToDomainMapper::map)
}