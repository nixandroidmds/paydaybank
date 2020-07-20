package com.payday.bank.data.api.source.impl

import com.payday.bank.data.api.ApiUnauthenticatedService
import com.payday.bank.data.api.entity.UserApiEntity
import com.payday.bank.data.api.source.base.AuthenticationApiSource
import javax.inject.Inject

class AuthenticationApiSourceImpl @Inject constructor(
    private val api: ApiUnauthenticatedService
) : AuthenticationApiSource {

    override suspend fun signIn(entity: UserApiEntity) = api.signIn(entity)

    override suspend fun signUp(entity: UserApiEntity) = api.signUp(entity)
}