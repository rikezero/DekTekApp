package com.rikezero.dektek.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rikezero.dektek.room.dao.CardCollectionDao
import com.rikezero.dektek.room.dao.CardCollectionMappingDao
import com.rikezero.dektek.room.dao.CardDao
import com.rikezero.dektek.room.entity.CardCollectionEntity
import com.rikezero.dektek.room.entity.CardCollectionMappingEntity
import com.rikezero.dektek.room.entity.CardEntity
import com.rikezero.dektek.room.typeconverter.CardTypeConverters

@Database(
    entities = [CardCollectionEntity::class, CardEntity::class, CardCollectionMappingEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CardTypeConverters::class)
abstract class DekTekDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
    abstract fun cardCollectionDao(): CardCollectionDao
    abstract fun cardCollectionMappingDao(): CardCollectionMappingDao
}
