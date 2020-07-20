package com.payday.bank.data.preferences.source.impl

import android.content.Context
import androidx.core.content.edit
import com.payday.bank.data.preferences.source.base.AuthenticationPreferencesSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthenticationPreferencesSourceImpl @Inject constructor(context: Context) :
    AuthenticationPreferencesSource {

    private val preferences by lazy {
        context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }

    override suspend fun hasToken() = withContext(Dispatchers.IO) {
        preferences.contains(TOKEN_VALUE)
    }

    override suspend fun getToken() = withContext(Dispatchers.IO) {
        preferences.getString(TOKEN_VALUE, null) ?: ""
    }

    override suspend fun saveToken(token: String?) = withContext(Dispatchers.IO) {
        preferences.edit(true) {
            if (token.isNullOrEmpty()) {
                remove(TOKEN_VALUE)
            } else {
                putString(TOKEN_VALUE, token)
            }
        }
    }

    override suspend fun clear() = withContext(Dispatchers.IO) {
        preferences.edit(true) { clear() }
    }

    companion object {

        private const val FILE_NAME = "Authentication"

        private const val TOKEN_VALUE = "TOKEN_VALUE"
    }
}
