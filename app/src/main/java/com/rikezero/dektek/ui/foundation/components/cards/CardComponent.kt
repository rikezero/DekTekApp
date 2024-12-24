package com.rikezero.dektek.ui.foundation.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.rikezero.dektek.ui.foundation.theme.DekTekTheme
import com.rikezero.dektek.util.thenIf
import com.valentinilk.shimmer.shimmer

@Composable
fun CardComponent(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
    ) {
        SubcomposeAsyncImage(
            model = "https://cards.scryfall.io/normal/front/e/d/eddf5231-7c46-417b-82c3-f6a8ede5ac9c.jpg?1712354581",
            contentDescription = "Image with shimmer placeholder",
            loading = {
                CardFramePlaceholder(isLoading = true)
            },
            error = {
                CardFramePlaceholder()
            },
            contentScale = ContentScale.FillBounds,
            alignment = Alignment.Center,
            modifier = Modifier
                .aspectRatio(63f / 88f)
                .wrapContentSize()
        )
    }
}

@Composable
fun CardFramePlaceholder(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
) {
    Surface(
        modifier = modifier
            .thenIf(
                condition = isLoading,
                ifTrue = { shimmer() }
            )
            .aspectRatio(63f / 88f),
        shape = DekTekTheme.shapes.large,
        color = DekTekTheme.colors.white500,
        border = BorderStroke(
            color = DekTekTheme.colors.black100,
            width = 24.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .padding(start = 8.dp, end = 8.dp)
                    .background(
                        color = DekTekTheme.colors.white400,
                        shape = RoundedCornerShape(14.dp)
                    )
                    .border(
                        border = BorderStroke(width = 2.dp, color = DekTekTheme.colors.black100),
                        shape = RoundedCornerShape(14.dp)
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(232.dp)
                    .offset(y = (-2).dp)
                    .padding(horizontal = 20.dp)
                    .background(color = DekTekTheme.colors.white400)
                    .border(BorderStroke(width = 2.dp, color = DekTekTheme.colors.black100))
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .offset(y = (-4).dp)
                    .padding(start = 8.dp, end = 8.dp)
                    .background(
                        color = DekTekTheme.colors.white400,
                        shape = RoundedCornerShape(14.dp)
                    )
                    .border(
                        border = BorderStroke(width = 2.dp, color = DekTekTheme.colors.black100),
                        shape = RoundedCornerShape(14.dp)
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .offset(y = (-6).dp)
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 8.dp)
                    .background(color = DekTekTheme.colors.white400)
                    .border(BorderStroke(width = 2.dp, color = DekTekTheme.colors.black100))
            )
        }
    }
}


@Preview(name = "CardComponent")
@Composable
private fun CardComponentPreview() {
    DekTekTheme {
        CardComponent()
    }
}