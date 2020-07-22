package com.payday.bank.data.repository.api

import com.payday.bank.data.repository.impl.api.entity.UserApiEntity
import retrofit2.http.Body
import retrofit2.http.POST

interface UnauthenticatedApiSource {

    @POST("authenticate")
    suspend fun signIn(@Body entity: UserApiEntity): UserApiEntity

    @POST("customers")
    suspend fun signUp(@Body entity: UserApiEntity): UserApiEntity
}