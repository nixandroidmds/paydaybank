package com.payday.bank.util.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import java.text.ParseException
import java.time.ZonedDateTime

class DateDeserializer : JsonDeserializer<ZonedDateTime> {

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ) =
        try {
            TODO("DateUtils.parseDatetime(json.asString)")
        } catch (e: ParseException) {
            throw JsonParseException(e)
        }
}