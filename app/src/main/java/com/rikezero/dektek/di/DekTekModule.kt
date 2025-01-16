package com.rikezero.dektek.di

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.rikezero.dektek.domain.repository.CardCollectionRepository
import com.rikezero.dektek.domain.repository.impl.CardCollectionRepositoryImpl
import com.rikezero.dektek.domain.typeadapter.CardModelAdapter
import com.rikezero.dektek.domain.usecase.CreateCollectionUseCase
import com.rikezero.dektek.domain.usecase.DeleteCollectionUseCase
import com.rikezero.dektek.domain.usecase.GetCollectionByNameUseCase
import com.rikezero.dektek.domain.usecase.GetCollectionsUseCase
import com.rikezero.dektek.networking.engine.NetworkEngine
import com.rikezero.dektek.networking.engine.impl.NetworkEngineImpl
import com.rikezero.dektek.networking.engine.retrofit.buildRetrofit
import com.rikezero.dektek.networking.networkadapter.NetworkAdapter
import com.rikezero.dektek.networking.networkadapter.impl.BasicNetworkAdapter
import com.rikezero.dektek.room.DekTekDatabase
import com.rikezero.dektek.room.adapter.DatabaseAdapter
import com.rikezero.dektek.room.adapter.impl.CardCollectionDatabaseAdapter
import com.rikezero.dektek.room.adapter.impl.CardCollectionMappingDatabaseAdapter
import com.rikezero.dektek.room.adapter.impl.CardsDatabaseAdapter
import com.rikezero.dektek.room.dao.CardCollectionDao
import com.rikezero.dektek.room.dao.CardCollectionMappingDao
import com.rikezero.dektek.room.dao.CardDao
import com.rikezero.dektek.room.entity.CardCollectionEntity
import com.rikezero.dektek.room.entity.CardCollectionMappingEntity
import com.rikezero.dektek.room.entity.CardEntity
import com.rikezero.dektek.room.typeconverter.CardTypeConverters
import com.rikezero.dektek.ui.collections.collectionslist.CardCollectionListViewModel
import com.rikezero.mtgapi_kotlin_sdk.domain.model.card.CardModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val NAME_RETROFIT_DEFAULT = "retrofit_default"
const val DEKTEK_DATABASE = "dektek-database"

val appModule = module {
    single { CardModelAdapter() }
    single {
        GsonBuilder()
            .registerTypeAdapter(CardModel::class.java, get<CardModelAdapter>())
            .setPrettyPrinting()
            .create()
    }
}

val roomModule = module {
    single { CardTypeConverters(get()) }
    single {
        Room.databaseBuilder(
            get(),
            DekTekDatabase::class.java,
            DEKTEK_DATABASE
        ).addTypeConverter(get<CardTypeConverters>())
            .build()
    }

    single<CardDao> { get<DekTekDatabase>().cardDao() }
    single<CardCollectionDao> { get<DekTekDatabase>().cardCollectionDao() }
    single<CardCollectionMappingDao> { get<DekTekDatabase>().cardCollectionMappingDao() }

    single { CardsDatabaseAdapter(cardDao = get()) }
    single { CardCollectionDatabaseAdapter(collectionDao = get()) }
    single { CardCollectionMappingDatabaseAdapter(mappingDao = get()) }
    single<DatabaseAdapter<CardEntity>> { get<CardsDatabaseAdapter>() }
    single<DatabaseAdapter<CardCollectionEntity>> { get<CardCollectionDatabaseAdapter>() }
    single<DatabaseAdapter<CardCollectionMappingEntity>> { get<CardCollectionMappingDatabaseAdapter>() }
}

val dekTekUseCaseModules = module {
    single { GetCollectionsUseCase(databaseRepository = get()) }
    single { DeleteCollectionUseCase(databaseRepository = get()) }
    single { GetCollectionByNameUseCase(databaseRepository = get()) }
    single { CreateCollectionUseCase(databaseRepository = get()) }
}

val dekTekRepositoryModules = module {
    single<CardCollectionRepository> {
        CardCollectionRepositoryImpl(
            collectionAdapter = get(),
            cardsAdapter = get(),
            mappingAdapter = get()
        )
    }
}

val dekTekNetworkingModules = module {
    single(named(NAME_RETROFIT_DEFAULT)) {
        buildRetrofit(
            host = "",
            interceptors = listOf()
        )
    }
    single<NetworkEngine> { NetworkEngineImpl(retrofit = get(named(NAME_RETROFIT_DEFAULT))) }
    single<NetworkAdapter> { BasicNetworkAdapter(networkEngine = get()) }
}

val viewModelModules = module {
    viewModel {
        CardCollectionListViewModel(
            getCardsUseCase = get(),
            createCollectionUseCase = get(),
            getCollectionsUseCase = get(),
            getCollectionByNameUseCase = get(),
            deleteCollectionUseCase = get(),
            dispatcher = Dispatchers.IO
        )
    }
}