package com.rikezero.dektek.domain.model

import androidx.room.Embedded
import com.rikezero.dektek.room.entity.CardEntity

data class CardWithQuantity(
    @Embedded val card: CardEntity,
    val quantity: Int
)
