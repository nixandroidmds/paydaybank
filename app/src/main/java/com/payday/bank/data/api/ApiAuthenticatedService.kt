package com.payday.bank.data.api

import com.payday.bank.data.api.entity.AccountApiEntity
import com.payday.bank.data.api.entity.TransactionApiEntity
import retrofit2.http.GET

interface ApiAuthenticatedService {

    @GET("accounts")
    suspend fun getAccountList(): List<AccountApiEntity>

    @GET("transactions")
    suspend fun getTransactionList(): List<TransactionApiEntity>
}