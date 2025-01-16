package com.rikezero.dektek.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rikezero.dektek.room.entity.CardEntity

/**
 * Data Access Object (DAO) for managing operations on the `CardEntity` table.
 *
 * This DAO provides methods to:
 * - Insert or replace a card in the database.
 * - Retrieve a card by its unique identifier.
 * - Delete a card by its unique identifier, if needed.
 *
 * ### Usage
 * 1. **Insert or Replace a Card**:
 *    Use the `insertOrUpdateCard` method to add a new card or update an existing one.
 *
 * 2. **Retrieve a Card**:
 *    Use the `getCardById` method to fetch a card's details using its unique `id`.
 *
 * 3. **Delete a Card**:
 *    Use the `deleteCardById` method to remove a card from the database.
 */
@Dao
interface CardDao {

    /**
     * Inserts a new card into the database or replaces an existing card with the same ID.
     *
     * This method uses the `REPLACE` strategy, which means:
     * - If the `id` already exists, the existing row will be overwritten.
     * - If the `id` does not exist, a new row will be created.
     *
     * @param card The `CardEntity` object to be inserted or updated.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateCard(card: CardEntity)

    /**
     * Retrieves a card by its unique ID.
     *
     * @param cardId The unique identifier of the card to retrieve.
     * @return The `CardEntity` object corresponding to the given ID, or `null` if no such card exists.
     */
    @Query("SELECT * FROM cards WHERE id = :cardId")
    suspend fun getCardById(cardId: String): CardEntity?

    /**
     * Retrieves all cards in the table.     *
     *
     * @return A list of `CardEntity` objects.
     */
    @Query("SELECT * FROM cards")
    suspend fun getAllCards(): List<CardEntity>

    /**
     * Deletes a card from the database by its unique ID.
     *
     * @param cardId The unique identifier of the card to delete.
     */
    @Query("DELETE FROM cards WHERE id = :cardId")
    suspend fun deleteCardById(cardId: String)
}
