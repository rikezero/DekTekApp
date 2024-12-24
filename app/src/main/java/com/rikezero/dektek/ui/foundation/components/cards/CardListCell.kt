package com.rikezero.dektek.ui.foundation.components.cards

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.rikezero.dektek.ui.foundation.theme.DekTekTheme
import com.rikezero.dektek.util.toDeserializedClass
import com.rikezero.mtgapi_kotlin_sdk.domain.model.card.CardModel
import com.rikezero.mtgapi_kotlin_sdk.domain.model.lists.CardListModel

@Composable
fun CardListCell(
    cardModel: CardModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RectangleShape
    ) {
        Column {
            Row {
                ProvideTextStyle(
                    value = DekTekTheme.typography.body2.copy(fontWeight = FontWeight.Bold)
                ) {
                    Text(
                        text = cardModel.name.orEmpty()
                    )
                }

            }
        }
    }
}

@Preview(name = "CardListCell")
@Preview(
    name = "CardListCell",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun CardListCellPreview() {
    val jsonString = """
{
  "cards": [
    {
      "name": "Archangel Avacyn",
      "names": [
        "Archangel Avacyn",
        "Avacyn, the Purifier"
      ],
      "manaCost": "{3}{W}{W}",
      "cmc": 5,
      "colors": [
        "White"
      ],
      "colorIdentity": [
        "W"
      ],
      "type": "Legendary Creature — Angel",
      "supertypes": [
        "Legendary"
      ],
      "types": [
        "Creature"
      ],
      "subtypes": [
        "Angel"
      ],
      "rarity": "Mythic Rare",
      "set": "SOI",
      "text": "Flash\nFlying, vigilance\nWhen Archangel Avacyn enters the battlefield, creatures you control gain indestructible until end of turn.\nWhen a non-Angel creature you control dies, transform Archangel Avacyn at the beginning of the next upkeep.",
      "artist": "James Ryman",
      "number": "5a",
      "power": "4",
      "toughness": "4",
      "layout": "double-faced",
      "multiverseid": 409741,
      "imageUrl": "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=409741&type=card",
      "rulings": [
        {
          "date": "2016-04-08",
          "text": "Archangel Avacyn’s delayed triggered ability triggers at the beginning of the next upkeep regardless of whose turn it is."
        },
        {
          "date": "2016-04-08",
          "text": "Archangel Avacyn’s delayed triggered ability won’t cause it to transform back into Archangel Avacyn if it has already transformed into Avacyn, the Purifier, perhaps because several creatures died in one turn."
        },
        {
          "date": "2016-04-08",
          "text": "For more information on double-faced cards, see the Shadows over Innistrad mechanics article (http://magic.wizards.com/en/articles/archive/feature/shadows-over-innistrad-mechanics)."
        }
      ],
      "foreignNames": [
        {
          "name": "大天使艾维欣",
          "language": "Chinese Simplified",
          "multiverseid": 410071
        },
        {
          "name": "大天使艾維欣",
          "language": "Chinese Traditional",
          "multiverseid": 410401
        },
        {
          "name": "Archange Avacyn",
          "language": "French",
          "multiverseid": 411061
        },
        {
          "name": "Erzengel Avacyn",
          "language": "German",
          "multiverseid": 410731
        },
        {
          "name": "Arcangelo Avacyn",
          "language": "Italian",
          "multiverseid": 411391
        },
        {
          "name": "大天使アヴァシン",
          "language": "Japanese",
          "multiverseid": 411721
        },
        {
          "name": "대천사 아바신",
          "language": "Korean",
          "multiverseid": 412051
        },
        {
          "name": "Arcanjo Avacyn",
          "language": "Portuguese (Brazil)",
          "multiverseid": 412381
        },
        {
          "name": "Архангел Авацина",
          "language": "Russian",
          "multiverseid": 412711
        },
        {
          "name": "Arcángel Avacyn",
          "language": "Spanish",
          "multiverseid": 413041
        }
      ],
      "printings": [
        "SOI"
      ],
      "originalText": "Flash\nFlying, vigilance\nWhen Archangel Avacyn enters the battlefield, creatures you control gain indestructible until end of turn.\nWhen a non-Angel creature you control dies, transform Archangel Avacyn at the beginning of the next upkeep.",
      "originalType": "Legendary Creature — Angel",
      "id": "02ea5ddc89d7847abc77a0fbcbf2bc74e6456559"
    }
  ]
}
""".trimIndent()
    val model = jsonString.toDeserializedClass<CardListModel>()

    DekTekTheme {
        LazyColumn {
            items(model?.cards.orEmpty()) {
                CardListCell(it)
            }
        }
    }
}