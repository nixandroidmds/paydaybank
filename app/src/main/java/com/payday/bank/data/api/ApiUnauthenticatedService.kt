package com.payday.bank.data.api

import com.payday.bank.data.api.entity.UserApiEntity
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiUnauthenticatedService {

    @POST("authenticate")
    suspend fun signIn(@Body entity: UserApiEntity): UserApiEntity

    @POST("customers")
    suspend fun signUp(@Body entity: UserApiEntity): UserApiEntity
}