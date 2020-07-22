package com.payday.bank.data.repository.preferences

interface AuthenticationPreferencesSource {

    suspend fun hasToken(): Boolean

    suspend fun getToken(): String

    suspend fun saveToken(token: String?)

    suspend fun clear()
}