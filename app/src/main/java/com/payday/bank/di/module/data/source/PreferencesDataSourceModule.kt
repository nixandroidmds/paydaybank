package com.payday.bank.di.module.data.source

import com.payday.bank.data.preferences.source.base.AuthenticationPreferencesSource
import com.payday.bank.data.preferences.source.impl.AuthenticationPreferencesSourceImpl
import dagger.Binds
import dagger.Module

@Module interface PreferencesDataSourceModule {

    @Binds
    fun bindAuthenticationPreferencesSource(source: AuthenticationPreferencesSourceImpl): AuthenticationPreferencesSource
}