package com.payday.bank.util.date

import timber.log.Timber
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object DateUtils {

    @Suppress("SpellCheckingInspection")
    private const val ZONED_DATE_TIME_FRACTION = "yyyy-MM-dd'T'kk:mm:ss.SSS'Z'"
    private const val ZONED_DATE_TIME = "yyyy-MM-dd'T'kk:mm:ss'Z'"
    private const val LOCAL_DATE = "d/MM/yyyy"

    private val ZONED_DATE_TIME_FRACTION_PATTERN =
        "^[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]{3}Z$".toRegex()
    private val ZONED_DATE_TIME_PATTERN =
        "^[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}Z$".toRegex()
    private val LOCAL_DATE_PATTERN =
        "^[0-9]{1,2}/[0-9]{2}/[0-9]{4}$".toRegex()

    private val ZONED_DATE_TIME_FRACTION_FORMATTER =
        DateTimeFormatter.ofPattern(ZONED_DATE_TIME_FRACTION)
    private val ZONED_DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern(ZONED_DATE_TIME)
    private val LOCAL_DATE_FORMATTER =
        DateTimeFormatter.ofPattern(LOCAL_DATE)

    fun parseUnknownDatetimeFormat(dateTime: String?): ZonedDateTime? =
        when {
            dateTime == null -> null

            ZONED_DATE_TIME_FRACTION_PATTERN.matches(dateTime) ->
                parseZonedDateTimeFractionFormat(dateTime)

            ZONED_DATE_TIME_PATTERN.matches(dateTime) ->
                parseZonedDateTimeFormat(dateTime)

            LOCAL_DATE_PATTERN.matches(dateTime) ->
                parseLocalDateFormat(dateTime)

            else -> null
        }?.atZone(ZoneId.systemDefault())

    private fun parseZonedDateTimeFractionFormat(dateTime: String) =
        runCatching {
            LocalDateTime.parse(dateTime, ZONED_DATE_TIME_FRACTION_FORMATTER)
        }.onFailure(Timber::e).getOrNull()

    private fun parseZonedDateTimeFormat(dateTime: String) =
        runCatching {
            LocalDateTime.parse(dateTime, ZONED_DATE_TIME_FORMATTER)
        }.onFailure(Timber::e).getOrNull()

    private fun parseLocalDateFormat(dateTime: String) =
        runCatching {
            LocalDateTime.parse(dateTime, LOCAL_DATE_FORMATTER)
        }.onFailure(Timber::e).getOrNull()

    fun formatToDate(timeUtcMs: Long?) =
        formatToDate(timeUtcMs?.let(Instant::ofEpochMilli)?.atZone(ZoneOffset.UTC))

    fun formatToDateTimeFraction(date: ZonedDateTime?) =
        if (date == null) {
            null
        } else {
            ZONED_DATE_TIME_FRACTION_FORMATTER.format(date)
        }

    fun formatToDateTime(date: ZonedDateTime?) =
        if (date == null) {
            null
        } else {
            ZONED_DATE_TIME_FORMATTER.format(date)
        }

    fun formatToDate(date: ZonedDateTime?) =
        if (date == null) {
            null
        } else {
            LOCAL_DATE_FORMATTER.format(date)
        }
}