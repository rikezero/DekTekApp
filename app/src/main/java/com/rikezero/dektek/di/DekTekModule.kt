package com.rikezero.dektek.di

import com.rikezero.dektek.networking.engine.NetworkEngine
import com.rikezero.dektek.networking.engine.impl.NetworkEngineImpl
import com.rikezero.dektek.networking.engine.retrofit.buildRetrofit
import com.rikezero.dektek.networking.networkadapter.NetworkAdapter
import com.rikezero.dektek.networking.networkadapter.impl.BasicNetworkAdapter
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val NAME_RETROFIT_DEFAULT = "retrofit_default"

val dekTekNetworkingModules = module {
    single(named(NAME_RETROFIT_DEFAULT)) {
        buildRetrofit(
            host = "",
            interceptors = listOf()
        )
    }
    single<NetworkEngine> { NetworkEngineImpl(retrofit = get()) }
    single<NetworkAdapter> { BasicNetworkAdapter(networkEngine = get()) }
}