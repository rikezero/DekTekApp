package com.rikezero.dektek.domain.typeadapter

import com.google.gson.*
import com.rikezero.mtgapi_kotlin_sdk.domain.model.card.CardModel
import com.rikezero.mtgapi_kotlin_sdk.domain.model.card.ForeignNameModel
import com.rikezero.mtgapi_kotlin_sdk.domain.model.card.RulingModel
import java.lang.reflect.Type

class CardModelAdapter : JsonSerializer<CardModel>, JsonDeserializer<CardModel> {
    override fun serialize(src: CardModel, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        val jsonObject = JsonObject()
        src.name?.let { jsonObject.addProperty("name", it) }
        src.manaCost?.let { jsonObject.addProperty("manaCost", it) }
        src.cmc?.let { jsonObject.addProperty("cmc", it) }
        src.colors?.let { jsonObject.add("colors", context.serialize(it)) }
        src.colorIdentity?.let { jsonObject.add("colorIdentity", context.serialize(it)) }
        src.type?.let { jsonObject.addProperty("type", it) }
        src.types?.let { jsonObject.add("types", context.serialize(it)) }
        src.superTypes?.let { jsonObject.add("superTypes", context.serialize(it)) }
        src.subTypes?.let { jsonObject.add("subTypes", context.serialize(it)) }
        src.rarity?.let { jsonObject.addProperty("rarity", it) }
        src.set?.let { jsonObject.addProperty("set", it) }
        src.setName?.let { jsonObject.addProperty("setName", it) }
        src.text?.let { jsonObject.addProperty("text", it) }
        src.flavor?.let { jsonObject.addProperty("flavor", it) }
        src.artist?.let { jsonObject.addProperty("artist", it) }
        src.number?.let { jsonObject.addProperty("number", it) }
        src.power?.let { jsonObject.addProperty("power", it) }
        src.toughness?.let { jsonObject.addProperty("toughness", it) }
        src.layout?.let { jsonObject.addProperty("layout", it) }
        src.multiverseId?.let { jsonObject.addProperty("multiverseId", it) }
        src.imageUrl?.let { jsonObject.addProperty("imageUrl", it) }
        src.rulings?.let { jsonObject.add("rulings", context.serialize(it)) }
        src.foreignNames?.let { jsonObject.add("foreignNames", context.serialize(it)) }
        src.printings?.let { jsonObject.add("printings", context.serialize(it)) }
        src.originalText?.let { jsonObject.addProperty("originalText", it) }
        src.originalType?.let { jsonObject.addProperty("originalType", it) }
        src.id?.let { jsonObject.addProperty("id", it) }
        return jsonObject
    }

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): CardModel {
        val jsonObject = json.asJsonObject
        return CardModel(
            name = jsonObject.get("name")?.asString,
            manaCost = jsonObject.get("manaCost")?.asString,
            cmc = jsonObject.get("cmc")?.asDouble,
            colors = jsonObject.get("colors")?.let { context.deserialize(it, List::class.java) as List<String> },
            colorIdentity = jsonObject.get("colorIdentity")?.let { context.deserialize(it, List::class.java) as List<String> },
            type = jsonObject.get("type")?.asString,
            types = jsonObject.get("types")?.let { context.deserialize(it, List::class.java) as List<String> },
            superTypes = jsonObject.get("superTypes")?.let { context.deserialize(it, List::class.java) as List<String> },
            subTypes = jsonObject.get("subTypes")?.let { context.deserialize(it, List::class.java) as List<String> },
            rarity = jsonObject.get("rarity")?.asString,
            set = jsonObject.get("set")?.asString,
            setName = jsonObject.get("setName")?.asString,
            text = jsonObject.get("text")?.asString,
            flavor = jsonObject.get("flavor")?.asString,
            artist = jsonObject.get("artist")?.asString,
            number = jsonObject.get("number")?.asString,
            power = jsonObject.get("power")?.asString,
            toughness = jsonObject.get("toughness")?.asString,
            layout = jsonObject.get("layout")?.asString,
            multiverseId = jsonObject.get("multiverseId")?.asInt,
            imageUrl = jsonObject.get("imageUrl")?.asString,
            rulings = jsonObject.get("rulings")?.let { context.deserialize(it, List::class.java) as List<RulingModel> },
            foreignNames = jsonObject.get("foreignNames")?.let { context.deserialize(it, List::class.java) as List<ForeignNameModel> },
            printings = jsonObject.get("printings")?.let { context.deserialize(it, List::class.java) as List<String> },
            originalText = jsonObject.get("originalText")?.asString,
            originalType = jsonObject.get("originalType")?.asString,
            id = jsonObject.get("id")?.asString
        )
    }
}

