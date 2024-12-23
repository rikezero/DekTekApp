package com.rikezero.dektek.networking.networkadapter.impl

import com.google.gson.JsonDeserializer
import com.rikezero.dektek.networking.engine.NetworkEngine
import com.rikezero.dektek.networking.networkadapter.NetworkAdapter
import com.rikezero.dektek.networking.response.DekTeKResponse
import kotlin.reflect.KClass

class BasicNetworkAdapter(
    private val networkEngine: NetworkEngine
): NetworkAdapter {
    override suspend fun <T : Any> get(
        url: String,
        headers: Map<String, String>,
        queryParams: Map<String, String>,
        responseClass: KClass<T>,
        deserializer: JsonDeserializer<T>?
    ): DekTeKResponse<T> {
        return networkEngine.get(
            url = url,
            headers = headers,
            queryParams = queryParams,
            responseClass = responseClass,
            deserializer = deserializer
        )
    }

    override suspend fun <T : Any> post(
        url: String,
        body: Any,
        headers: Map<String, String>,
        responseClass: KClass<T>,
        deserializer: JsonDeserializer<T>?
    ): DekTeKResponse<T> {
        return networkEngine.post(
            url = url,
            body = body,
            headers = headers,
            responseClass = responseClass,
            deserializer = deserializer
        )
    }

    override suspend fun <T : Any> put(
        url: String,
        body: Any,
        headers: Map<String, String>,
        responseClass: KClass<T>,
        deserializer: JsonDeserializer<T>?
    ): DekTeKResponse<T> {
        return networkEngine.put(
            url = url,
            body = body,
            headers = headers,
            responseClass = responseClass,
            deserializer = deserializer
        )
    }

    override suspend fun <T : Any> patch(
        url: String,
        body: Any,
        headers: Map<String, String>,
        responseClass: KClass<T>,
        deserializer: JsonDeserializer<T>?
    ): DekTeKResponse<T> {
        return networkEngine.patch(
            url = url,
            body = body,
            headers = headers,
            responseClass = responseClass,
            deserializer = deserializer
        )
    }

    override suspend fun <T : Any> delete(
        url: String,
        body: Any,
        headers: Map<String, String>,
        responseClass: KClass<T>,
        deserializer: JsonDeserializer<T>?
    ): DekTeKResponse<T> {
        return networkEngine.delete(
            url = url,
            body = body,
            headers = headers,
            responseClass = responseClass,
            deserializer = deserializer
        )
    }
}