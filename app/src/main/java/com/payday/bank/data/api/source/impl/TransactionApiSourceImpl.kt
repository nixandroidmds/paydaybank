package com.payday.bank.data.api.source.impl

import com.payday.bank.data.api.ApiAuthenticatedService
import com.payday.bank.data.api.source.base.TransactionApiSource
import javax.inject.Inject

class TransactionApiSourceImpl @Inject constructor(
    private val api: ApiAuthenticatedService
) : TransactionApiSource {

    override suspend fun getTransactionList() = api.getTransactionList()
}