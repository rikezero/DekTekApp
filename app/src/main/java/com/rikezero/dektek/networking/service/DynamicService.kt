package com.rikezero.dektek.networking.service

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.HeaderMap
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.QueryMap
import retrofit2.http.Url


interface DynamicService {

    @GET
    suspend fun get(
        @Url url: String,
        @HeaderMap headers: Map<String, String>,
        @QueryMap queryParams: Map<String, String>
    ): ResponseBody

    @POST
    suspend fun post(
        @Url url: String,
        @Body body: RequestBody,
        @HeaderMap headers: Map<String, String>
    ): ResponseBody

    @PUT
    suspend fun put(
        @Url url: String,
        @Body body: RequestBody,
        @HeaderMap headers: Map<String, String>
    ): ResponseBody

    @PATCH
    suspend fun patch(
        @Url url: String,
        @Body body: RequestBody,
        @HeaderMap headers: Map<String, String>
    ): ResponseBody

    @DELETE
    suspend fun delete(
        @Url url: String,
        @Body body: RequestBody,
        @HeaderMap headers: Map<String, String>
    ): ResponseBody
}
