package com.payday.bank.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.payday.bank.BuildConfig
import com.payday.bank.data.api.ApiService
import com.payday.bank.util.gson.DateDeserializer
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date
import javax.inject.Singleton

@Module class RetrofitModule {

    @Singleton @Provides
    fun provideGson(dateDeserializer: DateDeserializer): Gson =
        GsonBuilder()
            .registerTypeAdapter(Date::class.java, dateDeserializer)
            .apply { if (BuildConfig.DEBUG) setPrettyPrinting() }
            .create()

    @Singleton @Provides
    fun provideApiService(gson: Gson, client: OkHttpClient): ApiService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
}