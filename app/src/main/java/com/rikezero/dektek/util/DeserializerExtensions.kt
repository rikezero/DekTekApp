package com.rikezero.dektek.util

import com.google.gson.Gson

inline fun <reified T> String.toDeserializedClass(): T? {
    val gson = Gson()
    return try {
        gson.fromJson(this, T::class.java)
    } catch (e: Exception){
        null
    }
}