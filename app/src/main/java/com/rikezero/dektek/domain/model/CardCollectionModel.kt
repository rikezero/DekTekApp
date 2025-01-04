package com.rikezero.dektek.domain.model

import android.os.Parcelable
import com.rikezero.mtgapi_kotlin_sdk.domain.model.card.CardModel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class CardCollectionModel(
    val collectionName: String,
    val cards: @RawValue MutableMap<CardModel, Int> = mutableMapOf()
): Parcelable
