package com.payday.bank.data.api.entity

import com.google.gson.annotations.SerializedName
import java.time.ZonedDateTime

data class TransactionApiEntity(
    @SerializedName("id") val id: String? = null,
    @SerializedName("account_id") val accountId: String? = null,
    @SerializedName("amount") val amount: Double? = null,
    @SerializedName("vendor") val vendor: String? = null,
    @SerializedName("category") val category: String? = null,
    @SerializedName("date") val dateTime: ZonedDateTime? = null
)