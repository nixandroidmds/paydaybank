package com.payday.bank.util.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.payday.bank.util.date.DateUtils
import java.lang.reflect.Type
import java.text.ParseException
import java.time.ZonedDateTime
import javax.inject.Inject

class DateDeserializer @Inject constructor() : JsonDeserializer<ZonedDateTime> {

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ) =
        try {
            DateUtils.parseUnknownDatetimeFormat(json.asString)
        } catch (e: ParseException) {
            throw JsonParseException(e)
        }
}