package com.payday.bank.di.module.data.repository

import com.payday.bank.data.repository.base.AccountRepository
import com.payday.bank.data.repository.base.AuthenticationRepository
import com.payday.bank.data.repository.base.TransactionRepository
import com.payday.bank.data.repository.impl.AccountRepositoryImpl
import com.payday.bank.data.repository.impl.AuthenticationRepositoryImpl
import com.payday.bank.data.repository.impl.TransactionRepositoryImpl
import com.payday.bank.di.module.data.source.ApiDataSourceModule
import com.payday.bank.di.module.data.source.PreferencesDataSourceModule
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        ApiDataSourceModule::class,
        PreferencesDataSourceModule::class
    ]
)
interface RepositoryModule {

    @Binds
    fun bindAuthenticationRepository(repository: AuthenticationRepositoryImpl): AuthenticationRepository

    @Binds
    fun bindAccountRepository(repository: AccountRepositoryImpl): AccountRepository

    @Binds
    fun bindTransactionRepository(repository: TransactionRepositoryImpl): TransactionRepository
}