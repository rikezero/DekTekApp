package com.rikezero.dektek.domain.model

import android.os.Parcelable
import com.rikezero.mtgapi_kotlin_sdk.domain.model.card.CardModel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

/**
 * Represents a collection of cards, identified by a unique name and containing a map of cards and their respective quantities.
 *
 * This class is designed to model card collections in memory and is Parcelable, making it suitable for
 * passing between Android components (e.g., Activities, Fragments).
 *
 * ### Fields:
 * - `collectionName`: A unique name identifying the card collection.
 * - `cards`: A map where each key is a `CardModel` representing a card, and the value is the quantity of that card in the collection.
 *
 */
@Parcelize
data class CardCollectionModel(
    val collectionName: String,
    val cards: @RawValue MutableMap<CardModel, Int> = mutableMapOf()
): Parcelable
