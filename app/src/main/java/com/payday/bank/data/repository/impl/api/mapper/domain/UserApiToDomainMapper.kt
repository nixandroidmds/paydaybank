package com.payday.bank.data.repository.impl.api.mapper.domain

import com.payday.bank.data.repository.impl.api.entity.UserApiEntity
import com.payday.bank.domain.entity.Gender
import com.payday.bank.domain.entity.UserDomainEntity
import javax.inject.Inject

class UserApiToDomainMapper @Inject constructor() {

    fun map(entity: UserApiEntity) =
        UserDomainEntity(
            id = entity.id,
            firstName = entity.firstName,
            lastName = entity.lastName,
            gender = Gender.cast(entity.gender),
            email = entity.email,
            password = entity.password,
            zonedDateTime = entity.zonedDateTime,
            phone = entity.phone
        )
}