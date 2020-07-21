package com.payday.bank.data.repository.impl

import com.payday.bank.data.api.mapper.api.UserDomainToApiMapper
import com.payday.bank.data.api.mapper.domain.UserApiToDomainMapper
import com.payday.bank.data.api.source.base.AuthenticationApiSource
import com.payday.bank.data.preferences.source.base.AuthenticationPreferencesSource
import com.payday.bank.data.repository.base.AuthenticationRepository
import com.payday.bank.domain.entity.UserDomainEntity
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val apiSource: AuthenticationApiSource,
    private val preferencesSource: AuthenticationPreferencesSource,
    private val apiToDomainMapper: UserApiToDomainMapper,
    private val domainToApiMapper: UserDomainToApiMapper
) : AuthenticationRepository {

    override suspend fun hasToken() = preferencesSource.hasToken()

    override suspend fun getToken() = preferencesSource.getToken()

    override suspend fun signIn(entity: UserDomainEntity) =
        apiSource.signIn(domainToApiMapper.map(entity)).let(apiToDomainMapper::map)

    override suspend fun signUp(entity: UserDomainEntity) =
        apiSource.signUp(domainToApiMapper.map(entity)).let(apiToDomainMapper::map)

    override suspend fun signOut() {
        preferencesSource.clear()
    }
}