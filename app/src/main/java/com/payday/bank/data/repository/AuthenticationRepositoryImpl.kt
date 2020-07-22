package com.payday.bank.data.repository

import com.payday.bank.data.repository.api.UnauthenticatedApiSource
import com.payday.bank.data.repository.impl.api.mapper.api.UserDomainToApiMapper
import com.payday.bank.data.repository.impl.api.mapper.domain.UserApiToDomainMapper
import com.payday.bank.data.repository.preferences.AuthenticationPreferencesSource
import com.payday.bank.domain.entity.UserDomainEntity
import com.payday.bank.domain.repository.AuthenticationRepository
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val apiSource: UnauthenticatedApiSource,
    private val preferencesSource: AuthenticationPreferencesSource,
    private val apiToDomainMapper: UserApiToDomainMapper,
    private val domainToApiMapper: UserDomainToApiMapper
) : AuthenticationRepository {

    override suspend fun hasToken() = preferencesSource.hasToken()

    override suspend fun getToken() = preferencesSource.getToken()

    override suspend fun signIn(entity: UserDomainEntity) =
        apiSource
            .signIn(domainToApiMapper.map(entity))
            .also { preferencesSource.saveToken(it.id) }
            .let(apiToDomainMapper::map)

    override suspend fun signUp(entity: UserDomainEntity) =
        apiSource
            .signUp(domainToApiMapper.map(entity))
            .also { preferencesSource.saveToken(it.id) }
            .let(apiToDomainMapper::map)

    override suspend fun signOut() {
        preferencesSource.clear()
    }
}