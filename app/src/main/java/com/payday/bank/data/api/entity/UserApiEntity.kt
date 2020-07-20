package com.payday.bank.data.api.entity

import com.google.gson.annotations.SerializedName
import java.time.ZonedDateTime

data class UserApiEntity(
    @SerializedName("id") val id: String? = null,
    @SerializedName("First Name") val firstName: String? = null,
    @SerializedName("Last Name") val lastName: String? = null,
    @SerializedName("gender") val gender: String? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("password") val password: String? = null,
    @SerializedName("dob") val zonedDateTime: ZonedDateTime? = null,
    @SerializedName("phone") val phone: String? = null
)