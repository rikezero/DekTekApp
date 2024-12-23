package com.rikezero.dektek.networking.engine.impl

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonSyntaxException
import com.rikezero.dektek.networking.deserializer.DefaultDeserializer
import com.rikezero.dektek.networking.engine.NetworkEngine
import com.rikezero.dektek.networking.response.DekTeKResponse
import com.rikezero.dektek.networking.service.DynamicService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Retrofit
import kotlin.reflect.KClass

class NetworkEngineImpl(
    private val retrofit: Retrofit
): NetworkEngine {
    private val dynamicService: DynamicService = retrofit.create(DynamicService::class.java)

    override suspend fun <T : Any> get(
        url: String,
        headers: Map<String, String>,
        queryParams: Map<String, String>,
        responseClass: KClass<T>,
        deserializer: JsonDeserializer<T>?
    ): DekTeKResponse<T> = withContext(Dispatchers.IO) {
        try {
            val responseBody = dynamicService.get(url, headers, queryParams)
            parseResponse(responseBody, responseClass)
        } catch (e: Exception) {
            DekTeKResponse.Error(e)
        }
    }

    override suspend fun <T : Any> post(
        url: String,
        body: Any,
        headers: Map<String, String>,
        responseClass: KClass<T>,
        deserializer: JsonDeserializer<T>?
    ): DekTeKResponse<T> = withContext(Dispatchers.IO) {
        try {
            val requestBody = createRequestBody(body)
            val responseBody = dynamicService.post(url, requestBody, headers)
            parseResponse(responseBody, responseClass)
        } catch (e: Exception) {
            DekTeKResponse.Error(e)
        }
    }

    override suspend fun <T : Any> put(
        url: String,
        body: Any,
        headers: Map<String, String>,
        responseClass: KClass<T>,
        deserializer: JsonDeserializer<T>?
    ): DekTeKResponse<T> = withContext(Dispatchers.IO) {
        try {
            val requestBody = createRequestBody(body)
            val responseBody = dynamicService.put(url, requestBody, headers)
            parseResponse(responseBody, responseClass)
        } catch (e: Exception) {
            DekTeKResponse.Error(e)
        }
    }

    override suspend fun <T : Any> patch(
        url: String,
        body: Any,
        headers: Map<String, String>,
        responseClass: KClass<T>,
        deserializer: JsonDeserializer<T>?
    ): DekTeKResponse<T> = withContext(Dispatchers.IO) {
        try {
            val requestBody = createRequestBody(body)
            val responseBody = dynamicService.patch(url, requestBody, headers)
            parseResponse(responseBody, responseClass)
        } catch (e: Exception) {
            DekTeKResponse.Error(e)
        }
    }

    override suspend fun <T : Any> delete(
        url: String,
        body: Any,
        headers: Map<String, String>,
        responseClass: KClass<T>,
        deserializer: JsonDeserializer<T>?
    ): DekTeKResponse<T> = withContext(Dispatchers.IO) {
        try {
            val requestBody = createRequestBody(body)
            val responseBody = dynamicService.delete(url, requestBody, headers)
            parseResponse(responseBody, responseClass)
        } catch (e: Exception) {
            DekTeKResponse.Error(e)
        }
    }

    private fun createRequestBody(body: Any): RequestBody {
        val jsonBody = Gson().toJson(body)
        return jsonBody.toRequestBody("application/json".toMediaTypeOrNull())
    }

    private fun <T : Any> parseResponse(
        responseBody: ResponseBody,
        responseClass: KClass<T>,
        deserializer: JsonDeserializer<T>? = null
    ): DekTeKResponse<T> {
        return try {
            val responseString = responseBody.string()
            val gsonInstance = if (deserializer != null) {
                GsonBuilder()
                    .registerTypeAdapter(responseClass::class.java, deserializer)
                    .create()
            } else {
                DefaultDeserializer.getGson(responseClass)
            }
            val parsedResponse = gsonInstance.fromJson<T>(responseString, responseClass::class.java)
            DekTeKResponse.Success(parsedResponse)
        } catch (e: JsonSyntaxException) {
            DekTeKResponse.Error(e)
        }
    }
}