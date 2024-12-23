package com.rikezero.dektek.ui.foundation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rikezero.dektek.R

object DekTekTheme {
    val colors: DekTekColors
        @Composable
        @ReadOnlyComposable
        get() = LocalDekTeKColors.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalDekTekTypography.current

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalDekTekShapes.current
}

private val LocalDekTeKColors = staticCompositionLocalOf<DekTekColors> { error("No Colors Provided") }
private val LocalDekTekTypography = staticCompositionLocalOf<Typography> { error("No Typography provided") }
private val LocalDekTekShapes = staticCompositionLocalOf<Shapes> { error("No Shapes provided") }

@Composable
fun DekTekTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val montserratRegular = FontFamily(
        Font(R.font.montserrat_regular)
    )

    val montserratBold = FontFamily(
        Font(R.font.montserrat_bold)
    )

    val typography = Typography(
        defaultFontFamily = montserratRegular,
        h1 = TextStyle(
            fontFamily = montserratBold,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        ),
        h2 = TextStyle(
            fontFamily = montserratBold,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        ),
        h3 = TextStyle(
            fontFamily = montserratRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp
        ),
        h4 = TextStyle(
            fontFamily = montserratRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp
        ),
        h5 = TextStyle(
            fontFamily = montserratRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        h6 = TextStyle(
            fontFamily = montserratRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        subtitle1 = TextStyle(
            fontFamily = montserratRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        subtitle2 = TextStyle(
            fontFamily = montserratRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        body1 = TextStyle(
            fontFamily = montserratRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        body2 = TextStyle(
            fontFamily = montserratRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        button = TextStyle(
            fontFamily = montserratBold,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        ),
        caption = TextStyle(
            fontFamily = montserratRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),
        overline = TextStyle(
            fontFamily = montserratRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp
        )
    )

    val colorScheme: DekTekColors = if (darkTheme) DekTekColors.darkColors() else DekTekColors.lightColors()

    val rememberedColors = remember { colorScheme.toMaterialColors(darkTheme) }

    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(8.dp),
        large = RoundedCornerShape(16.dp)
    )
    CompositionLocalProvider(
        LocalDekTeKColors provides colorScheme,
        LocalDekTekTypography provides typography,
        LocalDekTekShapes provides shapes
    ) {
        MaterialTheme(
            colors = rememberedColors,
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}

fun DekTekColors.toMaterialColors(darkTheme: Boolean) = when(darkTheme){
    true -> darkColors(
        primary = primary,
        secondary = secondary,
        background = background,
        surface = surface,
        error = error500,
        onPrimary = onPrimary,
        onSecondary = onSecondary,
        onBackground = onBackground,
        onSurface = onSurface,
        onError = onError
    )
    false -> lightColors(
        primary = primary,
        secondary = secondary,
        background = background,
        surface = surface,
        error = error500,
        onPrimary = onPrimary,
        onSecondary = onSecondary,
        onBackground = onBackground,
        onSurface = onSurface,
        onError = onError
    )
}

