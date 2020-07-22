package com.payday.bank.data.repository

import com.payday.bank.data.repository.api.AuthenticatedApiSource
import com.payday.bank.data.repository.impl.api.mapper.domain.TransactionApiToDomainMapper
import com.payday.bank.domain.entity.AccountDomainEntity
import com.payday.bank.domain.entity.TransactionDomainEntity
import com.payday.bank.domain.repository.TransactionRepository
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val apiSource: AuthenticatedApiSource,
    private val apiToDomainMapper: TransactionApiToDomainMapper
) : TransactionRepository {

    override suspend fun getTransactionList(
        accountList: List<AccountDomainEntity>
    ): List<TransactionDomainEntity> {
        val accountIdList = accountList.map(AccountDomainEntity::id)

        return apiSource
            .getTransactionList()
            .asSequence()
            .filter { accountIdList.contains(it.accountId) }
            .map { apiToDomainMapper.map(it, accountList) }
            .toList()
    }
}