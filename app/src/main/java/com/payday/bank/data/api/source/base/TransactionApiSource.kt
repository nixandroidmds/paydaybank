package com.payday.bank.data.api.source.base

import com.payday.bank.data.api.entity.TransactionApiEntity

interface TransactionApiSource {

    suspend fun getTransactionList(): List<TransactionApiEntity>
}