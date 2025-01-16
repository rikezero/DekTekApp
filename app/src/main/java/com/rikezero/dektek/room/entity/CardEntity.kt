package com.rikezero.dektek.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rikezero.mtgapi_kotlin_sdk.domain.model.card.ForeignNameModel
import com.rikezero.mtgapi_kotlin_sdk.domain.model.card.RulingModel

/**
 * Represents a card entity in the database, storing all relevant details about a card.
 *
 * This entity is stored in the `cards` table and contains detailed information about
 * individual cards such as their name, attributes, type, and associated metadata.
 *
 * @property id Unique identifier for the card (e.g., multiverse ID or custom UUID).
 * @property name The name of the card.
 * @property manaCost The mana cost to play the card, represented as a string (e.g., "{3}{W}{W}").
 * @property cmc Converted Mana Cost (CMC) of the card, represented as a decimal number.
 * @property colors List of colors associated with the card (e.g., ["White", "Blue"]).
 * @property colorIdentity List of color identity symbols for deck building rules (e.g., ["W", "U"]).
 * @property type Full type description of the card (e.g., "Legendary Creature — Angel").
 * @property types List of primary types of the card (e.g., ["Creature", "Enchantment"]).
 * @property superTypes List of super types of the card (e.g., ["Legendary", "Basic"]).
 * @property subTypes List of subtypes of the card (e.g., ["Angel", "Warrior"]).
 * @property rarity Rarity of the card (e.g., "Common", "Mythic Rare").
 * @property set Set code that the card belongs to (e.g., "SOI" for Shadows over Innistrad).
 * @property setName Full name of the set that the card belongs to.
 * @property text Rules text or abilities of the card, if applicable.
 * @property flavor Flavor text providing thematic context, if applicable.
 * @property artist The name of the artist who illustrated the card.
 * @property number The card's number within its set (e.g., "5a").
 * @property power The power value of the card, if applicable (e.g., "4").
 * @property toughness The toughness value of the card, if applicable (e.g., "4").
 * @property layout The layout type of the card (e.g., "normal", "double-faced").
 * @property multiverseId The card's multiverse ID, a unique identifier in Magic: The Gathering.
 * @property imageUrl The URL for the card's image.
 * @property printings List of set codes where the card has been printed (e.g., ["SOI", "AKH"]).
 * @property originalText The original text of the card before any errata or changes.
 * @property originalType The original type of the card before any errata or changes.
 */
@Entity(tableName = "cards")
data class CardEntity(
    @PrimaryKey val id: String,
    val name: String?,
    val manaCost: String?,
    val cmc: Double?,
    val colors: List<String>?,
    val colorIdentity: List<String>?,
    val type: String?,
    val types: List<String>?,
    val superTypes: List<String>?,
    val subTypes: List<String>?,
    val rarity: String?,
    val set: String?,
    val setName: String?,
    val text: String?,
    val flavor: String?,
    val artist: String?,
    val number: String?,
    val power: String?,
    val toughness: String?,
    val layout: String?,
    val multiverseId: Int?,
    val imageUrl: String?,
    val printings: List<String>?,
    val originalText: String?,
    val originalType: String?,
    val rulings: List<RulingModel>?,
    val foreignNames: List<ForeignNameModel>?,
)
