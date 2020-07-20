package com.payday.bank.data.api.source.base

import com.payday.bank.data.api.entity.AccountApiEntity

interface AccountApiSource {

    suspend fun getAccountList(): List<AccountApiEntity>
}