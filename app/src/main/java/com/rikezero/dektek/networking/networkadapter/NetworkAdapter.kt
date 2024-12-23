package com.rikezero.dektek.networking.networkadapter

import com.google.gson.JsonDeserializer
import com.rikezero.dektek.networking.response.DekTeKResponse
import kotlin.reflect.KClass

interface NetworkAdapter {
    suspend fun <T : Any> get(
        url: String,
        headers: Map<String, String> = emptyMap(),
        queryParams: Map<String, String> = emptyMap(),
        responseClass: KClass<T>,
        deserializer: JsonDeserializer<T>? = null
    ): DekTeKResponse<T>

    suspend fun <T : Any> post(
        url: String,
        body: Any,
        headers: Map<String, String> = emptyMap(),
        responseClass: KClass<T>,
        deserializer: JsonDeserializer<T>? = null
    ): DekTeKResponse<T>

    suspend fun <T : Any> put(
        url: String,
        body: Any,
        headers: Map<String, String> = emptyMap(),
        responseClass: KClass<T>,
        deserializer: JsonDeserializer<T>? = null
    ): DekTeKResponse<T>

    suspend fun <T : Any> patch(
        url: String,
        body: Any,
        headers: Map<String, String> = emptyMap(),
        responseClass: KClass<T>,
        deserializer: JsonDeserializer<T>? = null
    ): DekTeKResponse<T>

    suspend fun <T : Any> delete(
        url: String,
        body: Any,
        headers: Map<String, String> = emptyMap(),
        responseClass: KClass<T>,
        deserializer: JsonDeserializer<T>? = null
    ): DekTeKResponse<T>
}