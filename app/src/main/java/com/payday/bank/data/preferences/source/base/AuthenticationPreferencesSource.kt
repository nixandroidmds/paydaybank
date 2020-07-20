package com.payday.bank.data.preferences.source.base

interface AuthenticationPreferencesSource {

    suspend fun hasToken(): Boolean

    suspend fun getToken(): String

    suspend fun saveToken(token: String?)

    suspend fun clear()
}