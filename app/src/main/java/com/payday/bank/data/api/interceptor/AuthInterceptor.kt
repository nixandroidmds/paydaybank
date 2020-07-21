package com.payday.bank.data.api.interceptor

import com.payday.bank.data.repository.base.AuthenticationRepository
import com.payday.bank.domain.exception.TokenExpiredException
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class AuthInterceptor @Inject constructor(
    private val userRepository: AuthenticationRepository
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            userRepository.runCatching { getToken() }.onFailure(Timber::e).getOrNull()
        }

        if (token.isNullOrEmpty()) {
            throw TokenExpiredException()
        }

        val request = chain
            .request()
        // TODO: add authorization token
        //  .newBuilder()
        //  .addHeader(AUTHORIZATION_HEADER_REQUEST, token)
        //  .build()

        return chain.proceed(request)
    }

    // TODO: add authorization token
    //  companion object {
    //      private const val AUTHORIZATION_HEADER_REQUEST = "AUTHORIZATION_HEADER_REQUEST"
    //  }
}
