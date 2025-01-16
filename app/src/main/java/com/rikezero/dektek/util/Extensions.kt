package com.rikezero.dektek.util

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.rikezero.dektek.R
import com.rikezero.dektek.domain.model.CardCollectionModel
import com.rikezero.dektek.room.entity.CardCollectionEntity
import com.rikezero.dektek.room.entity.CardEntity
import com.rikezero.mtgapi_kotlin_sdk.domain.model.card.CardModel

/**
 * Extracts all substrings enclosed by curly braces `{}` from the current [String].
 *
 * For example:
 * ```
 * val input = "{3}{W}{W}"
 * val symbols = input.geSymbolsList()
 * // symbols: ["3", "W", "W"]
 * ```
 *
 * This uses a regular expression to find all occurrences of `{...}`, then extracts
 * everything between the braces.
 *
 * @receiver The [String] from which to extract bracketed content.
 * @return A [List] of [String] entries found inside curly braces.
 */
fun String.geSymbolsList(): List<String> {
    // Regular expression to match content inside {}
    val regex = Regex("\\{(.*?)}")
    // Find all matches and extract the content inside the brackets
    return regex.findAll(this).map { it.groupValues[1] }.toList()
}

/**
 * Converts all bracketed symbols in the current [String] into a list of [ManaValue] enums.
 *
 * This function first parses the string to extract symbols via [geSymbolsList].
 * It then attempts to convert each extracted symbol into a [ManaValue] by calling
 * [ManaValue.fromString]. If conversion fails, it will catch the exception silently
 * (with `runCatching`), and the symbol is effectively skipped and handled by onFailure block.
 *
 * For example:
 * ```
 * val input = "{3}{W}{2G}{XYZ}"
 * val manaValues = input.getManaSymbols()
 * // manaValues: [THREE, W, TWO_G]  // 'XYZ' fails and is omitted
 * ```
 *
 * @receiver The [String] containing bracketed mana symbols to convert.
 * @return A [MutableList] of valid [ManaValue] enums extracted from the string.
 */
fun String.getManaSymbols(): MutableList<ManaValue> {
    val symbolsList = this.geSymbolsList()
    val manaSymbols = mutableListOf<ManaValue>()

    symbolsList.forEach { symbol ->
        runCatching {
            ManaValue.fromString(symbol)
        }.onSuccess { manaSymbol ->
            manaSymbols.add(manaSymbol)
        }.onFailure {
            // TODO: Implement proper error handling (e.g., logging with Timber or Crashlytics)
        }
    }
    return manaSymbols
}

fun TextStyle.makeItBold() = this.copy(
    fontFamily = FontFamily(
        Font(R.font.montserrat_bold)
    ),
    fontWeight = FontWeight.Bold
)

fun CardCollectionEntity.toModel(): CardCollectionModel {
    val entity = this
    return CardCollectionModel(
        collectionName = entity.collectionName
    )
}

fun CardCollectionModel.toEntity(): CardCollectionEntity {
    return CardCollectionEntity(
        collectionName = collectionName
    )
}

fun CardEntity.toModel() = CardModel(
    name = name,
    manaCost = manaCost,
    cmc = cmc,
    colors = colors,
    colorIdentity = colorIdentity,
    type = type,
    types = types,
    superTypes = superTypes,
    subTypes = subTypes,
    rarity = rarity,
    set = set,
    setName = setName,
    text = text,
    flavor = flavor,
    artist = artist,
    number = number,
    power = power,
    toughness = toughness,
    layout = layout,
    multiverseId = multiverseId,
    imageUrl = imageUrl,
    rulings = rulings,
    foreignNames = foreignNames,
    printings = printings,
    originalText = originalText,
    originalType = originalType,
    id = id
)