package com.payday.bank.data.repository.impl

import com.payday.bank.data.api.mapper.domain.TransactionApiToDomainMapper
import com.payday.bank.data.api.source.base.TransactionApiSource
import com.payday.bank.data.repository.base.TransactionRepository
import com.payday.bank.domain.entity.AccountDomainEntity
import com.payday.bank.domain.entity.TransactionDomainEntity
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val apiSource: TransactionApiSource,
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