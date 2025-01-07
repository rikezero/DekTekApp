package com.rikezero.dektek.application

import android.app.Application
import com.rikezero.dektek.di.dekTekNetworkingModules
import com.rikezero.dektek.di.viewModelModules
import com.rikezero.mtgapi_kotlin_sdk.di.startMtgApiLibrary
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.loadKoinModules

class DekTekApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DekTekApplication)
        }
        startMtgApiLibrary()
        loadAppModules()
    }

    private fun loadAppModules() {
        loadKoinModules(
            listOf(
                dekTekNetworkingModules,
                viewModelModules
            )
        )
    }
}