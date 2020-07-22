package com.payday.bank.data.repository.impl.api.mapper.api

import com.payday.bank.data.repository.impl.api.entity.UserApiEntity
import com.payday.bank.domain.entity.UserDomainEntity
import javax.inject.Inject

class UserDomainToApiMapper @Inject constructor() {

    fun map(entity: UserDomainEntity) =
        UserApiEntity(
            id = entity.id,
            firstName = entity.firstName,
            lastName = entity.lastName,
            gender = entity.gender?.gender,
            email = entity.email,
            password = entity.password,
            zonedDateTime = entity.zonedDateTime,
            phone = entity.phone
        )
}