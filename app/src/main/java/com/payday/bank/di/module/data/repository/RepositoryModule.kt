package com.payday.bank.di.module.data.repository

import com.payday.bank.data.repository.AccountRepositoryImpl
import com.payday.bank.data.repository.AuthenticationRepositoryImpl
import com.payday.bank.data.repository.TransactionRepositoryImpl
import com.payday.bank.di.module.data.source.ApiDataSourceModule
import com.payday.bank.di.module.data.source.PreferencesDataSourceModule
import com.payday.bank.domain.repository.AccountRepository
import com.payday.bank.domain.repository.AuthenticationRepository
import com.payday.bank.domain.repository.TransactionRepository
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