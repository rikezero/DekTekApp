package com.rikezero.dektek.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rikezero.dektek.room.entity.CardCollectionEntity

/**
 * Data Access Object (DAO) for managing operations on the `CardCollectionEntity` table.
 *
 * This DAO provides methods to:
 * - Insert a new card collection.
 * - Retrieve a card collection by its name.
 * - Retrieve all card collections in the database.
 * - Update an existing card collection.
 * - Delete a card collection by its name.
 *
 * ### Usage
 * 1. **Insert a Collection**:
 *    Use the `insertCollection` method to add a new collection to the database.
 *
 * 2. **Retrieve a Collection by Name**:
 *    Use the `getCollectionByName` method to fetch a collection's details using its unique name.
 *
 * 3. **Retrieve All Collections**:
 *    Use the `getAllCollections` method to get a list of all card collections in the database.
 *
 * 4. **Update a Collection**:
 *    Use the `updateCollection` method to modify the name of an existing collection.
 *
 * 5. **Delete a Collection**:
 *    Use the `deleteCollectionByName` method to remove a collection from the database.
 */
@Dao
interface CardCollectionDao {

    /**
     * Inserts a new card collection into the database. If a collection with the same name already exists,
     * it will be replaced.
     *
     * @param collection The `CardCollectionEntity` object to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCollection(collection: CardCollectionEntity)

    /**
     * Retrieves a card collection by its unique name.
     *
     * @param name The name of the card collection to retrieve.
     * @return The `CardCollectionEntity` corresponding to the given name, or `null` if no such collection exists.
     */
    @Query("SELECT * FROM card_collections WHERE collectionName = :name")
    suspend fun getCollectionByName(name: String): CardCollectionEntity?

    /**
     * Retrieves all card collections from the database.
     *
     * @return A list of all `CardCollectionEntity` objects in the database.
     */
    @Query("SELECT * FROM card_collections")
    suspend fun getAllCollections(): List<CardCollectionEntity>

    /**
     * Updates the name of an existing card collection.
     *
     * @param oldName The current name of the card collection to update.
     * @param newName The new name to assign to the card collection.
     */
    @Query("UPDATE card_collections SET collectionName = :newName WHERE collectionName = :oldName")
    suspend fun updateCollection(oldName: String, newName: String)

    /**
     * Deletes a card collection by its unique name.
     *
     * @param name The name of the card collection to delete.
     */
    @Query("DELETE FROM card_collections WHERE collectionName = :name")
    suspend fun deleteCollectionByName(name: String)
}
