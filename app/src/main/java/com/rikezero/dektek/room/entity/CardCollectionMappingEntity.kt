package com.rikezero.dektek.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey

/**
 * Represents the mapping between a card collection and the cards it contains.
 *
 * This entity is stored in the `collection_card_mapping` table and establishes
 * a many-to-many relationship between `CardCollectionEntity` and `CardEntity`.
 * Each row specifies how many copies of a card belong to a specific collection.
 *
 * ### Foreign Key Constraints:
 * - `collectionName`: References the `collectionName` in the `CardCollectionEntity` table.
 *   Deleting a collection will cascade and remove its mappings.
 * - `cardId`: References the `id` in the `CardEntity` table.
 *   Deleting a card will cascade and remove its mappings.
 *
 * @property collectionName The name of the card collection this mapping belongs to.
 *                          This is a foreign key referencing `CardCollectionEntity.collectionName`.
 * @property cardId The unique identifier of the card in the collection.
 *                  This is a foreign key referencing `CardEntity.id`.
 * @property quantity The number of copies of the card in the collection.
 */
@Entity(
    tableName = "collection_card_mapping",
    primaryKeys = ["collectionName", "cardId"],
    foreignKeys = [
        ForeignKey(
            entity = CardCollectionEntity::class,
            parentColumns = ["collectionName"],
            childColumns = ["collectionName"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CardEntity::class,
            parentColumns = ["id"],
            childColumns = ["cardId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CardCollectionMappingEntity(
    val collectionName: String,
    val cardId: String,
    val quantity: Int
)
