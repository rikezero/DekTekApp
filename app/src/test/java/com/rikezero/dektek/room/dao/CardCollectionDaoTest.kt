package com.rikezero.dektek.room.dao

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.GsonBuilder
import com.rikezero.dektek.di.KoinInjector.get
import com.rikezero.dektek.domain.typeadapter.CardModelAdapter
import com.rikezero.dektek.room.DekTekDatabase
import com.rikezero.dektek.room.entity.CardCollectionEntity
import com.rikezero.dektek.room.typeconverter.CardTypeConverters
import com.rikezero.mtgapi_kotlin_sdk.domain.model.card.CardModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class CardCollectionDaoTest {

    private lateinit var database: DekTekDatabase
    private lateinit var dao: CardCollectionDao
    private val card = mockk<CardModel>(relaxed = true) {
        every { name } returns "Archangel Avacyn"
    }

    @Before
    fun setup() {
        startKoin {
            modules(
                module {
                    single { CardModelAdapter() }
                    single {
                        GsonBuilder()
                            .registerTypeAdapter(CardModel::class.java, get<CardModelAdapter>())
                            .setPrettyPrinting()
                            .create()
                    }
                    single { CardTypeConverters(get()) }
                }
            )
        }
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(
            context,
            DekTekDatabase::class.java
        ).addTypeConverter(get<CardTypeConverters>())
            .allowMainThreadQueries().build()

        dao = database.cardCollectionDao()
    }

    @After
    fun tearDown() {
        database.close()
        stopKoin()
    }


    @Test
    fun givenACardCollectionWhenItIsInsertedAndRetrievedThenTheDataShouldMatch() = runBlocking {
        val collection = CardCollectionEntity(collectionName = "MyCollection")

        dao.insertCollection(collection)
        val retrievedCollection = dao.getCollectionByName("MyCollection")

        assertNotNull(retrievedCollection)
        assertEquals("MyCollection", retrievedCollection?.collectionName)
    }

    @Test
    fun givenAnExistingCardCollectionWhenItIsDeletedThenShouldReturnNull() = runBlocking {
        val collectionName = "DeleteCollection"
        val collection = CardCollectionEntity(collectionName = collectionName)

        dao.insertCollection(collection)

        dao.deleteCollectionByName(collectionName)
        val retrievedCollection = dao.getCollectionByName(collectionName)

        assertEquals(null, retrievedCollection)
    }
}
