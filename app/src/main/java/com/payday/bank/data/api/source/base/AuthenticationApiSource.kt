package com.payday.bank.data.api.source.base

import com.payday.bank.data.api.entity.UserApiEntity

interface AuthenticationApiSource {

    suspend fun signIn(entity: UserApiEntity): UserApiEntity

    suspend fun signUp(entity: UserApiEntity): UserApiEntity
}