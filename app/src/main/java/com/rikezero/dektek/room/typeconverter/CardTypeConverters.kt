package com.rikezero.dektek.room.typeconverter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rikezero.mtgapi_kotlin_sdk.domain.model.card.ForeignNameModel
import com.rikezero.mtgapi_kotlin_sdk.domain.model.card.RulingModel

@ProvidedTypeConverter
class CardTypeConverters(private val gson: Gson) {

    // List<String> Converters
    @TypeConverter
    fun fromStringList(list: List<String>?): String? {
        return list?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toStringList(json: String?): List<String>? {
        return json?.let { gson.fromJson(it, object : TypeToken<List<String>>() {}.type) }
    }

    // List<RulingModel> Converters
    @TypeConverter
    fun fromRulingModelList(list: List<RulingModel>?): String? {
        return list?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toRulingModelList(json: String?): List<RulingModel>? {
        return json?.let { gson.fromJson(it, object : TypeToken<List<RulingModel>>() {}.type) }
    }

    // List<ForeignNameModel> Converters
    @TypeConverter
    fun fromForeignNameModelList(list: List<ForeignNameModel>?): String? {
        return list?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toForeignNameModelList(json: String?): List<ForeignNameModel>? {
        return json?.let { gson.fromJson(it, object : TypeToken<List<ForeignNameModel>>() {}.type) }
    }
}
