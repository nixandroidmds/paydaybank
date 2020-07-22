package com.payday.bank.di.module.data.source

import com.google.gson.Gson
import com.payday.bank.BuildConfig
import com.payday.bank.data.repository.api.AuthenticatedApiSource
import com.payday.bank.data.repository.api.UnauthenticatedApiSource
import com.payday.bank.di.module.data.NetworkModule
import com.payday.bank.di.qualifier.http.Authenticated
import com.payday.bank.di.qualifier.http.Unauthenticated
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class ApiDataSourceModule {

    @Singleton @Provides
    fun provideUnauthenticatedApiSource(
        gson: Gson,
        @Unauthenticated client: OkHttpClient
    ): UnauthenticatedApiSource =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(UnauthenticatedApiSource::class.java)

    @Singleton @Provides
    fun provideAuthenticatedApiSource(
        gson: Gson,
        @Authenticated client: OkHttpClient
    ): AuthenticatedApiSource =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(AuthenticatedApiSource::class.java)
}