package com.rikezero.dektek.application

import android.app.Application
import com.rikezero.dektek.di.appModule
import com.rikezero.dektek.di.dekTekNetworkingModules
import com.rikezero.dektek.di.dekTekRepositoryModules
import com.rikezero.dektek.di.dekTekUseCaseModules
import com.rikezero.dektek.di.roomModule
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
                appModule,
                roomModule,
                dekTekNetworkingModules,
                dekTekRepositoryModules,
                dekTekUseCaseModules,
                viewModelModules
            )
        )
    }
}