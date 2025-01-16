package com.rikezero.dektek.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.rikezero.dektek.domain.model.CardWithQuantity
import com.rikezero.dektek.room.entity.CardCollectionMappingEntity

/**
 * Data Access Object (DAO) for managing the mapping between card collections and cards.
 *
 * This DAO provides methods to:
 * - Insert or update a mapping between a card collection and a card with its quantity.
 * - Retrieve all cards and their quantities for a specific card collection.
 * - Update the quantity of a specific card in a collection.
 * - Delete a mapping between a collection and a card, if needed.
 *
 * ### Usage
 * 1. **Insert or Update a Mapping**:
 *    Use the `insertOrUpdateMapping` method to add a new card to a collection or update its quantity.
 *
 * 2. **Retrieve Cards for a Collection**:
 *    Use the `getCardsForCollection` method to get a map of cards and their quantities for a specific collection.
 *
 * 3. **Delete a Mapping**:
 *    Use the `deleteMapping` method to remove a specific card from a collection.
 */
@Dao
interface CardCollectionMappingDao {

    /**
     * Inserts or updates a mapping between a collection and a card.
     *
     * If the mapping already exists, the quantity will be updated to reflect the new value.
     *
     * @param mapping The `CardCollectionMappingEntity` object containing the collection name, card ID, and quantity.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateMapping(mapping: CardCollectionMappingEntity)

    /**
     * Retrieves all cards and their quantities for a specific collection.
     *
     * @param collectionName The name of the card collection.
     * @return A list of `CardWithQuantity` that will later be mapped into a map.
     */
    @Transaction
    @Query(
        """
    SELECT cards.*, collection_card_mapping.quantity 
    FROM cards 
    INNER JOIN collection_card_mapping ON cards.id = collection_card_mapping.cardId
    WHERE collection_card_mapping.collectionName = :collectionName
"""
    )
    suspend fun getCardsWithQuantities(collectionName: String): List<CardWithQuantity>


    /**
     * Deletes a mapping between a collection and a card.
     *
     * @param collectionName The name of the card collection.
     * @param cardId The unique identifier of the card.
     */
    @Query(
        """
        DELETE FROM collection_card_mapping 
        WHERE collectionName = :collectionName AND cardId = :cardId
    """
    )
    suspend fun deleteMapping(collectionName: String, cardId: String)
}
