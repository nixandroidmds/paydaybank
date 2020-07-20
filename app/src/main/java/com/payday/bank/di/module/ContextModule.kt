package com.payday.bank.di.module

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module class ContextModule {

    @Provides @Singleton
    fun provideResources(context: Context): Resources =
        context.resources
}