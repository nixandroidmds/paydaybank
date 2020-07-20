package com.payday.bank.di.module.data.source

import com.payday.bank.data.api.source.base.AccountApiSource
import com.payday.bank.data.api.source.base.AuthenticationApiSource
import com.payday.bank.data.api.source.base.TransactionApiSource
import com.payday.bank.data.api.source.impl.AccountApiSourceImpl
import com.payday.bank.data.api.source.impl.AuthenticationApiSourceImpl
import com.payday.bank.data.api.source.impl.TransactionApiSourceImpl
import com.payday.bank.di.module.data.NetworkModule
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class])
interface ApiDataSourceModule {

    @Binds
    fun bindAuthenticationApiSource(source: AuthenticationApiSourceImpl): AuthenticationApiSource

    @Binds
    fun bindAccountApiSource(source: AccountApiSourceImpl): AccountApiSource

    @Binds
    fun bindTransactionApiSource(source: TransactionApiSourceImpl): TransactionApiSource
}