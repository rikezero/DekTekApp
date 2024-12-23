package com.rikezero.dektek.di

import org.koin.core.component.KoinComponent

object KoinInjector: KoinComponent{
    inline fun <reified T : Any>get() = getKoin().get<T>()
}