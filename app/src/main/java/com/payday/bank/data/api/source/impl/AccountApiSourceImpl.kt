package com.payday.bank.data.api.source.impl

import com.payday.bank.data.api.ApiAuthenticatedService
import com.payday.bank.data.api.entity.AccountApiEntity
import com.payday.bank.data.api.source.base.AccountApiSource
import javax.inject.Inject

class AccountApiSourceImpl @Inject constructor(
    private val api: ApiAuthenticatedService
) : AccountApiSource {

    override suspend fun getAccountList(): List<AccountApiEntity> = api.getAccountList()
}