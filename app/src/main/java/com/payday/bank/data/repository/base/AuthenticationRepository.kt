package com.payday.bank.data.repository.base

import com.payday.bank.domain.entity.UserDomainEntity

interface AuthenticationRepository {

    suspend fun hasToken(): Boolean

    suspend fun getToken(): String

    suspend fun signIn(entity: UserDomainEntity): UserDomainEntity

    suspend fun signUp(entity: UserDomainEntity): UserDomainEntity

    suspend fun signOut()
}