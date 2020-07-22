package com.payday.bank.data.repository.impl.api.interceptor

import android.content.res.Resources
import com.payday.bank.R
import com.payday.bank.domain.exception.MessageException
import com.payday.bank.domain.exception.NetworkException
import com.payday.bank.util.http.HttpCodes
import com.payday.bank.util.http.HttpHeader
import com.payday.bank.util.media.MimeType
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.security.cert.CertPathValidatorException
import javax.inject.Inject
import javax.inject.Singleton
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException

@Singleton class ErrorInterceptor @Inject constructor(
    private val resources: Resources
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var url: String? = null
        return try {
            val request = chain.request()
            url = request.url.toString()
            chain.proceed(request).proceed(url)
        } catch (exception: CertPathValidatorException) {
            throw NetworkException(
                messageForUser = resources.getString(R.string.ssl_handshake_error),
                logMessage = "CertPathValidatorException: $url",
                cause = exception
            )
        } catch (exception: SSLHandshakeException) {
            throw NetworkException(
                messageForUser = resources.getString(R.string.ssl_handshake_error),
                logMessage = "SSLHandshakeException: $url",
                cause = exception
            )
        } catch (exception: SSLException) {
            throw NetworkException(
                messageForUser = resources.getString(R.string.ssl_handshake_error),
                logMessage = "NotSecureConnectionException: $url",
                cause = exception
            )
        } catch (exception: ConnectException) {
            throw NetworkException(
                messageForUser = resources.getString(R.string.internet_error),
                logMessage = "InternetUnavailableException: $url",
                cause = exception
            )
        } catch (exception: UnknownHostException) {
            throw NetworkException(
                messageForUser = resources.getString(R.string.internet_error),
                logMessage = "ServerUnreachableException: $url",
                cause = exception
            )
        } catch (exception: SocketTimeoutException) {
            throw NetworkException(
                messageForUser = resources.getString(R.string.internet_error),
                logMessage = "InternetTimeoutException: $url",
                cause = exception
            )
        } catch (exception: NetworkException) {
            throw exception
        } catch (exception: IOException) {
            throw NetworkException(
                messageForUser = resources.getString(R.string.unknown_error),
                logMessage = "UnexpectedNetworkException: $url",
                cause = exception
            )
        } catch (exception: MessageException) {
            throw exception
        } catch (throwable: Throwable) {
            throw NetworkException(
                messageForUser = resources.getString(R.string.unknown_error),
                logMessage = "Throwable: $url",
                cause = throwable
            )
        }
    }

    private fun Response.proceed(url: String): Response {
        when {
            code == HttpCodes.FORBIDDEN || code == HttpCodes.UNAUTHORIZED -> {
                throw NetworkException(
                    messageForUser = resources.getString(R.string.access_unauthorized_error),
                    logMessage = getServerError(url, code)
                )
            }

            code == HttpCodes.BAD_REQUEST ||
                    code == HttpCodes.PAYMENT_REQUIRED ||
                    code == HttpCodes.NOT_FOUND ||
                    code == HttpCodes.UNPROCESSABLE_ENTITY -> {
                throw NetworkException(
                    messageForUser = resources.getString(R.string.unknown_error),
                    logMessage = getServerError(url, code)
                )
            }

            header(HttpHeader.HEADER_KEY_CONTENT_TYPE)?.contains(MimeType.TEXT_HTML) == true -> {
                throw NetworkException(
                    messageForUser = resources.getString(R.string.unknown_error),
                    logMessage = "HTML Page (Invalid response, need ticket for server): $url"
                )
            }

            code == HttpCodes.OK ||
                    code == HttpCodes.CREATED ||
                    code == HttpCodes.ACCEPTED ||
                    code == HttpCodes.NO_CONTENT -> {
                // Success
            }

            else -> throw NetworkException(
                messageForUser = resources.getString(R.string.unknown_error),
                logMessage = getServerError(url, code)
            )
        }
        return this
    }

    private fun getServerError(url: String, code: Int) = "Server Error with code: $code; $url"
}