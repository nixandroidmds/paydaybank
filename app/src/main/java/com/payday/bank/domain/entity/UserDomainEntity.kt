package com.payday.bank.domain.entity

import java.time.ZonedDateTime

data class UserDomainEntity(
    val id: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val gender: Gender? = null,
    val email: String?,
    val password: String?,
    val zonedDateTime: ZonedDateTime? = null,
    val phone: String? = null
)

sealed class Gender(val gender: String) {

    object Male : Gender(MALE_STR)
    object Female : Gender(FEMALE_STR)

    companion object {

        private const val MALE_STR = "male"
        private const val FEMALE_STR = "female"

        fun cast(gender: String?): Gender? =
            when (gender) {
                MALE_STR -> Male
                FEMALE_STR -> Female
                else -> null
            }

        fun cast(position: Int): Gender? =
            when (position) {
                0 -> null
                1 -> Male
                2 -> Female
                else -> null
            }
    }
}