package com.payday.bank.data.api.interceptor

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
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException

class ErrorInterceptor @Inject constructor(
    private val resources: Resources
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var url: String? = null
        return try {
            val request = chain.request()
            url = request.url.host + request.url.encodedQuery
            chain.proceed(request).proceed(url)
        } catch (exception: CertPathValidatorException) {
            throw NetworkException(
                message = resources.getString(R.string.ssl_handshake_error),
                logMessage = "CertPathValidatorException: $url",
                cause = exception
            )
        } catch (exception: SSLHandshakeException) {
            throw NetworkException(
                message = resources.getString(R.string.ssl_handshake_error),
                logMessage = "SSLHandshakeException: $url",
                cause = exception
            )
        } catch (exception: SSLException) {
            throw NetworkException(
                message = resources.getString(R.string.ssl_handshake_error),
                logMessage = "NotSecureConnectionException: $url",
                cause = exception
            )
        } catch (exception: ConnectException) {
            throw NetworkException(
                message = resources.getString(R.string.internet_error),
                logMessage = "InternetUnavailableException: $url",
                cause = exception
            )
        } catch (exception: UnknownHostException) {
            throw NetworkException(
                message = resources.getString(R.string.internet_error),
                logMessage = "ServerUnreachableException: $url",
                cause = exception
            )
        } catch (exception: SocketTimeoutException) {
            throw NetworkException(
                message = resources.getString(R.string.internet_error),
                logMessage = "InternetTimeoutException: $url",
                cause = exception
            )
        } catch (exception: NetworkException) {
            throw exception
        } catch (exception: IOException) {
            throw NetworkException(
                message = resources.getString(R.string.unknown_error),
                logMessage = "UnexpectedNetworkException: $url",
                cause = exception
            )
        } catch (exception: MessageException) {
            throw exception
        } catch (throwable: Throwable) {
            throw NetworkException(
                message = resources.getString(R.string.unknown_error),
                logMessage = "Throwable: $url",
                cause = throwable
            )
        }
    }

    private fun Response.proceed(url: String): Response {
        when {
            code == HttpCodes.FORBIDDEN || code == HttpCodes.UNAUTHORIZED -> {
                throw MessageException(
                    message = resources.getString(R.string.access_unauthorized),
                    logMessage = getServerError(code)
                )
            }

            code == HttpCodes.BAD_REQUEST ||
                    code == HttpCodes.PAYMENT_REQUIRED ||
                    code == HttpCodes.NOT_FOUND ||
                    code == HttpCodes.UNPROCESSABLE_ENTITY -> {
                TODO(url + "custom process error")
            }

            header(HttpHeader.HEADER_KEY_CONTENT_TYPE)?.contains(MimeType.TEXT_HTML) == true -> {
                throw MessageException(
                    message = resources.getString(R.string.unknown_error),
                    logMessage = "HTML Page (Invalid response, need ticket for server): $url"
                )
            }

            code == HttpCodes.OK ||
                    code == HttpCodes.CREATED ||
                    code == HttpCodes.ACCEPTED ||
                    code == HttpCodes.NO_CONTENT -> {
                // Success
            }

            else -> throw MessageException(message = getServerError(code))
        }
        return this
    }

    private fun getServerError(code: Int) = "Server Error with code: $code"
}