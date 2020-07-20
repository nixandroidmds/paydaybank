package com.payday.bank.di.module.data

import com.payday.bank.BuildConfig
import com.payday.bank.data.api.interceptor.AuthInterceptor
import com.payday.bank.data.api.interceptor.ErrorInterceptor
import com.payday.bank.di.qualifier.http.Authenticated
import com.payday.bank.di.qualifier.http.Unauthenticated
import dagger.Module
import dagger.Provides
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
class NetworkModule {

    @Provides @Singleton
    fun provideCertificatePinner(): CertificatePinner =
        CertificatePinner.Builder()
            .apply { BuildConfig.CERTIFICATE_ARR.forEach { add(BuildConfig.BASE_URL, it) } }
            .build()

    @Provides @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
        return interceptor
    }

    @Provides @Singleton @Unauthenticated
    fun provideOkHttpUnauthenticatedClient(httpClientBuilder: OkHttpClient.Builder): OkHttpClient =
        httpClientBuilder.build()

    @Provides @Singleton @Authenticated
    fun provideOkHttpAuthenticatedClient(
        httpClientBuilder: OkHttpClient.Builder,
        authInterceptor: AuthInterceptor
    ): OkHttpClient =
        httpClientBuilder
            .addInterceptor(authInterceptor)
            .build()

    @Provides
    fun provideBaseOkHttpClientBuilder(
        loggingInterceptor: HttpLoggingInterceptor,
        errorInterceptor: ErrorInterceptor,
        certificatePinner: CertificatePinner
    ): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .addInterceptor(errorInterceptor)
            .addInterceptor(loggingInterceptor)
            .apply {
                if (BuildConfig.CERTIFICATE_ARR.isNotEmpty()) {
                    certificatePinner(certificatePinner)
                }
            }
}