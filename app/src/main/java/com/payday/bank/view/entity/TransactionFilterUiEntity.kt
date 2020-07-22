package com.payday.bank.view.entity

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

data class TransactionFilterUiEntity(
    val ibanList: List<String> = listOf(),
    val dateFrom: ZonedDateTime = DATE_FROM_DEFAULT,
    private val _dateTo: ZonedDateTime? = null,
    val activeVisible: Boolean = true,
    val inactiveVisible: Boolean = false
) {

    val dateTo: ZonedDateTime
        get() = _dateTo ?: LocalDateTime
            .now()
            .atZone(ZoneId.systemDefault())
            .withHour(23)
            .withMinute(59)

    val hasDateTo get() = _dateTo != null

    companion object {

        private const val DATE_FROM_MONTH_BEFORE_MONTH = 3L
        private const val DATE_FROM_FIRST_DAY_IN_MONTH = 1

        private val DATE_FROM_DEFAULT = LocalDate
            .now()
            .minusMonths(DATE_FROM_MONTH_BEFORE_MONTH)
            .withDayOfMonth(DATE_FROM_FIRST_DAY_IN_MONTH)
            .atTime(0, 0)
            .atZone(ZoneId.systemDefault())
    }
}