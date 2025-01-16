package com.rikezero.dektek.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a card collection entity in the database.
 *
 * This entity is stored in the `card_collections` table and serves as the primary representation
 * of a named collection of cards. It acts as a grouping mechanism for cards within the database.
 *
 * @property collectionName The unique name of the card collection. This serves as the primary key for the table.
 */
@Entity(tableName = "card_collections")
data class CardCollectionEntity(
    @PrimaryKey val collectionName: String,
)
