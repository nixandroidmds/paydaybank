package com.payday.bank.data.repository.impl.api.mapper.domain

import com.payday.bank.data.repository.impl.api.entity.AccountApiEntity
import com.payday.bank.domain.entity.AccountDomainEntity
import javax.inject.Inject

class AccountApiToDomainMapper @Inject constructor() {

    fun map(entity: AccountApiEntity) =
        AccountDomainEntity(
            id = entity.id,
            customerId = entity.customerId,
            iban = entity.iban,
            type = entity.type,
            createdDateTime = entity.createdDateTime,
            active = entity.active ?: false
        )
}