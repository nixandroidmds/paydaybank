package com.payday.bank.util.gson

import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.payday.bank.util.date.DateUtils
import java.lang.reflect.Type
import java.time.ZonedDateTime
import javax.inject.Inject

class DateSerializer @Inject constructor() : JsonSerializer<ZonedDateTime?> {

    override fun serialize(
        src: ZonedDateTime?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonPrimitive? {
        val srcStr = DateUtils.formatToDateTimeFraction(src)
        return if (srcStr == null) null else JsonPrimitive(srcStr)
    }
}