package com.payday.bank.di.module.data.source

import com.payday.bank.data.repository.impl.preferences.AuthenticationPreferencesSourceImpl
import com.payday.bank.data.repository.preferences.AuthenticationPreferencesSource
import dagger.Binds
import dagger.Module

@Module interface PreferencesDataSourceModule {

    @Binds
    fun bindAuthenticationPreferencesSource(source: AuthenticationPreferencesSourceImpl): AuthenticationPreferencesSource
}