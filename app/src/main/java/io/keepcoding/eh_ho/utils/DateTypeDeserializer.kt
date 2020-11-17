package io.keepcoding.eh_ho.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class DateTypeDeserializer : JsonDeserializer<Date> {

    private val DATE_FORMATS = arrayOf(
        "yyyy-MM-dd'T'HH:mm:ssZ",
        "yyyy-MM-dd'T'HH:mm:ss",
        "yyyy-MM-dd",
        "EEE MMM dd HH:mm:ss z yyyy",
        "HH:mm:ss",
        "MM/dd/yyyy HH:mm:ss aaa",
        "yyyy-MM-dd'T'HH:mm:ss.SSSSSS",
        "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS",
        "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'",
        "MMM d',' yyyy H:mm:ss a"
    )

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date {
        json?.let { jsonElement ->
            for (format: String? in DATE_FORMATS) {
                try {
                    return SimpleDateFormat(
                        format,
                        Locale.getDefault()
                    ).parse(jsonElement.getAsString())
                } catch (e: ParseException) {
                }
            }
        }

        throw JsonParseException(
            "Unparseable date: \"" + json?.getAsString()
                .toString() + "\". Supported formats: \n" + Arrays.toString(DATE_FORMATS)
        )
    }
}