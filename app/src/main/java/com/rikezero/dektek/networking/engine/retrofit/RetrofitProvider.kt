package com.rikezero.dektek.networking.engine.retrofit

import com.google.gson.GsonBuilder
import com.rikezero.dektek.networking.engine.okhttp.buildHttpClient
import com.rikezero.dektek.networking.engine.retrofit.strategy.AnnotationExclusionStrategy
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun buildRetrofit(
    host: String,
    interceptors: List<Interceptor>,
    timeout: Long? = 30000L,
    okHttpClientConfigs: (OkHttpClient.Builder.() -> Unit)? = null,
    retrofitConfigs: (Retrofit.Builder.() -> Unit)? = null
): Retrofit = retrofitClient(
    host = host,
    retrofitConfigs = retrofitConfigs,
    httpClient = buildHttpClient(
        interceptors = interceptors,
        timeout = timeout,
        okHttpClientConfigs = okHttpClientConfigs
    )
)

private fun retrofitClient(
    host: String,
    httpClient: OkHttpClient,
    retrofitConfigs: (Retrofit.Builder.() -> Unit)? = null
): Retrofit = Retrofit.Builder()
    .baseUrl(host)
    .client(httpClient)
    .addConverterFactory(
        GsonConverterFactory.create(
            GsonBuilder()
                .setExclusionStrategies(AnnotationExclusionStrategy())
                .create()
        )
    ).apply {
        retrofitConfigs?.run { retrofitConfigs() }
    }.build()