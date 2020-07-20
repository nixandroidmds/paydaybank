package com.payday.bank.di.module

import com.payday.bank.BuildConfig
import com.payday.bank.data.api.interceptor.ErrorInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import javax.inject.Singleton

@Module class NetworkModule {

    @Provides @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
        return interceptor
    }

    @Singleton @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        errorInterceptor: ErrorInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(errorInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
}