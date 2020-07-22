package com.payday.bank.data.repository.api

import com.payday.bank.data.repository.impl.api.entity.AccountApiEntity
import com.payday.bank.data.repository.impl.api.entity.TransactionApiEntity
import retrofit2.http.GET

interface AuthenticatedApiSource {

    @GET("accounts")
    suspend fun getAccountList(): List<AccountApiEntity>

    @GET("transactions")
    suspend fun getTransactionList(): List<TransactionApiEntity>
}