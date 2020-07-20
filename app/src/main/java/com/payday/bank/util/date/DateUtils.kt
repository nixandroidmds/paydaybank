package com.payday.bank.util.date

import timber.log.Timber
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object DateUtils {

    @Suppress("SpellCheckingInspection")
    private const val ZONED_DATE_TIME_FRACTION = "yyyy-MM-dd'T'hh:mm:ss.SSSZ"
    private const val ZONED_DATE_TIME = "yyyy-MM-dd'T'hh:mm:ssZ"
    private const val LOCAL_DATE = "d/MM/yyyy"

    private val zonedDateTimeFractionFormatter =
        DateTimeFormatter.ofPattern(ZONED_DATE_TIME_FRACTION)
    private val zonedDateTimeFormatter =
        DateTimeFormatter.ofPattern(ZONED_DATE_TIME)
    private val localDateFormatter =
        DateTimeFormatter.ofPattern(LOCAL_DATE)

    fun parseUnknownDatetimeFormat(dateTime: String?): ZonedDateTime? =
        if (dateTime == null) {
            null
        } else {
            parseZonedDateTimeFractionFormat(dateTime)
                    ?: parseZonedDateTimeFormat(dateTime)
                    ?: parseLocalDateFormat(dateTime)
        }

    private fun parseZonedDateTimeFractionFormat(dateTime: String) =
        runCatching {
            ZonedDateTime.parse(dateTime, zonedDateTimeFractionFormatter)
        }.getOrElse { parseZonedDateTimeFormat(dateTime) }

    private fun parseZonedDateTimeFormat(dateTime: String) =
        runCatching {
            ZonedDateTime.parse(dateTime, zonedDateTimeFormatter)
        }.getOrElse { parseLocalDateFormat(dateTime) }

    private fun parseLocalDateFormat(dateTime: String) =
        runCatching {
            ZonedDateTime.parse(dateTime, localDateFormatter)
        }.onFailure(Timber::e).getOrNull()
}