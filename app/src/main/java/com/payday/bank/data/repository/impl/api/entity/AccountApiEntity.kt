package com.payday.bank.data.repository.impl.api.entity

import com.google.gson.annotations.SerializedName
import java.time.ZonedDateTime

data class AccountApiEntity(
    @SerializedName("id") val id: String? = null,
    @SerializedName("customer_id") val customerId: String? = null,
    @SerializedName("iban") val iban: String? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("date_created") val createdDateTime: ZonedDateTime? = null,
    @SerializedName("active") val active: Boolean? = null
)