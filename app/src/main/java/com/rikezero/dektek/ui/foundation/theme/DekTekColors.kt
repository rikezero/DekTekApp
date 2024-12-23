package com.rikezero.dektek.ui.foundation.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Stable
class DekTekColors(
    primary: Color,
    secondary: Color,
    background: Color,
    surface: Color,
    onPrimary: Color,
    onSecondary: Color,
    onBackground: Color,
    onSurface: Color,
    error500: Color,
    error400: Color,
    error300: Color,
    error200: Color,
    error100: Color,
    onError: Color,
    success500: Color,
    success400: Color,
    success300: Color,
    success200: Color,
    success100: Color,
    onSuccess: Color,
    white500: Color,
    white400: Color,
    white300: Color,
    white200: Color,
    white100: Color,
    white: Color,
    black: Color,
    black500: Color,
    black400: Color,
    black300: Color,
    black200: Color,
    black100: Color,
    darkOpacity500: Color,
    darkOpacity400: Color,
    darkOpacity300: Color,
    darkOpacity200: Color,
    darkOpacity100: Color,
    lightOpacity500: Color,
    lightOpacity400: Color,
    lightOpacity300: Color,
    lightOpacity200: Color,
    lightOpacity100: Color,
    celestialBlue500: Color,
    celestialBlue400: Color,
    celestialBlue300: Color,
    celestialBlue200: Color,
    celestialBlue100: Color,
    isDark: Boolean
) {
    // Core Colors
    var primary by mutableStateOf(primary)
        private set
    var secondary by mutableStateOf(secondary)
        private set
    var background by mutableStateOf(background)
        private set
    var surface by mutableStateOf(surface)
        private set

    // On Colors
    var onPrimary by mutableStateOf(onPrimary)
        private set
    var onSecondary by mutableStateOf(onSecondary)
        private set
    var onBackground by mutableStateOf(onBackground)
        private set
    var onSurface by mutableStateOf(onSurface)
        private set

    // Error Colors
    var error500 by mutableStateOf(error500)
        private set
    var error400 by mutableStateOf(error400)
        private set
    var error300 by mutableStateOf(error300)
        private set
    var error200 by mutableStateOf(error200)
        private set
    var error100 by mutableStateOf(error100)
        private set
    var onError by mutableStateOf(onError)
        private set

    // Success Colors
    var success500 by mutableStateOf(success500)
        private set
    var success400 by mutableStateOf(success400)
        private set
    var success300 by mutableStateOf(success300)
        private set
    var success200 by mutableStateOf(success200)
        private set
    var success100 by mutableStateOf(success100)
        private set
    var onSuccess by mutableStateOf(onSuccess)
        private set

    // White Shades
    var white500 by mutableStateOf(white500)
        private set
    var white400 by mutableStateOf(white400)
        private set
    var white300 by mutableStateOf(white300)
        private set
    var white200 by mutableStateOf(white200)
        private set
    var white100 by mutableStateOf(white100)
        private set

    // Black Shades
    var black500 by mutableStateOf(black500)
        private set
    var black400 by mutableStateOf(black400)
        private set
    var black300 by mutableStateOf(black300)
        private set
    var black200 by mutableStateOf(black200)
        private set
    var black100 by mutableStateOf(black100)
        private set

    // Celestial Blue Shades
    var celestialBlue500 by mutableStateOf(celestialBlue500)
        private set
    var celestialBlue400 by mutableStateOf(celestialBlue400)
        private set
    var celestialBlue300 by mutableStateOf(celestialBlue300)
        private set
    var celestialBlue200 by mutableStateOf(celestialBlue200)
        private set
    var celestialBlue100 by mutableStateOf(celestialBlue100)
        private set

    //Base
    var black by mutableStateOf(black)
        private set
    var white by mutableStateOf(white)
        private set

    // Opacity
    var darkOpacity500 by mutableStateOf(darkOpacity500)
        private set
    var darkOpacity400 by mutableStateOf(darkOpacity400)
        private set
    var darkOpacity300 by mutableStateOf(darkOpacity300)
        private set
    var darkOpacity200 by mutableStateOf(darkOpacity200)
        private set
    var darkOpacity100 by mutableStateOf(darkOpacity100)
        private set

    var lightOpacity500 by mutableStateOf(lightOpacity500)
        private set
    var lightOpacity400 by mutableStateOf(lightOpacity400)
        private set
    var lightOpacity300 by mutableStateOf(lightOpacity300)
        private set
    var lightOpacity200 by mutableStateOf(lightOpacity200)
        private set
    var lightOpacity100 by mutableStateOf(lightOpacity100)
        private set

    // Dark Mode Flag
    var isDark by mutableStateOf(isDark)
        private set

    companion object {
        fun lightColors(
            primary: Color = Primary,
            secondary: Color = Secondary,
            background: Color = Background,
            surface: Color = Surface,
            onPrimary: Color = OnPrimary,
            onSecondary: Color = OnSecondary,
            onBackground: Color = OnBackground,
            onSurface: Color = OnSurface,
            error500: Color = Error500,
            error400: Color = Error400,
            error300: Color = Error300,
            error200: Color = Error200,
            error100: Color = Error100,
            onError: Color = OnError,
            success500: Color = Success500,
            success400: Color = Success400,
            success300: Color = Success300,
            success200: Color = Success200,
            success100: Color = Success100,
            onSuccess: Color = OnSuccess,
            white500: Color = White500,
            white400: Color = White400,
            white300: Color = White300,
            white200: Color = White200,
            white100: Color = White100,
            white: Color = White,
            black: Color = Black,
            black500: Color = Black500,
            black400: Color = Black400,
            black300: Color = Black300,
            black200: Color = Black200,
            black100: Color = Black100,
            darkOpacity500: Color = DarkOpacity500,
            darkOpacity400: Color = DarkOpacity400,
            darkOpacity300: Color = DarkOpacity300,
            darkOpacity200: Color = DarkOpacity200,
            darkOpacity100: Color = DarkOpacity100,
            lightOpacity500: Color = LightOpacity500,
            lightOpacity400: Color = LightOpacity400,
            lightOpacity300: Color = LightOpacity300,
            lightOpacity200: Color = LightOpacity200,
            lightOpacity100: Color = LightOpacity100,
            celestialBlue500: Color = CelestialBlue500,
            celestialBlue400: Color = CelestialBlue400,
            celestialBlue300: Color = CelestialBlue300,
            celestialBlue200: Color = CelestialBlue200,
            celestialBlue100: Color = CelestialBlue100,
        ): DekTekColors = DekTekColors(
            primary = primary,
            secondary = secondary,
            background = background,
            surface = surface,
            onPrimary = onPrimary,
            onSecondary = onSecondary,
            onBackground = onBackground,
            onSurface = onSurface,
            error500 = error500,
            error400 = error400,
            error300 = error300,
            error200 = error200,
            error100 = error100,
            onError = onError,
            success500 = success500,
            success400 = success400,
            success300 = success300,
            success200 = success200,
            success100 = success100,
            onSuccess = onSuccess,
            white500 = white500,
            white400 = white400,
            white300 = white300,
            white200 = white200,
            white100 = white100,
            white = white,
            black = black,
            black500 = black500,
            black400 = black400,
            black300 = black300,
            black200 = black200,
            black100 = black100,
            darkOpacity500 = darkOpacity500,
            darkOpacity400 = darkOpacity400,
            darkOpacity300 = darkOpacity300,
            darkOpacity200 = darkOpacity200,
            darkOpacity100 = darkOpacity100,
            lightOpacity500 = lightOpacity500,
            lightOpacity400 = lightOpacity400,
            lightOpacity300 = lightOpacity300,
            lightOpacity200 = lightOpacity200,
            lightOpacity100 = lightOpacity100,
            celestialBlue500 = celestialBlue500,
            celestialBlue400 = celestialBlue400,
            celestialBlue300 = celestialBlue300,
            celestialBlue200 = celestialBlue200,
            celestialBlue100 = celestialBlue100,
            isDark = false
        )

        fun darkColors(
            primary: Color = PrimaryDark,
            secondary: Color = SecondaryDark,
            background: Color = BackgroundDark,
            surface: Color = SurfaceDark,
            onPrimary: Color = OnPrimaryDark,
            onSecondary: Color = OnSecondaryDark,
            onBackground: Color = OnBackgroundDark,
            onSurface: Color = OnSurfaceDark,
            error500: Color = Error500,
            error400: Color = Error400,
            error300: Color = Error300,
            error200: Color = Error200,
            error100: Color = Error100,
            onError: Color = OnError,
            success500: Color = Success500,
            success400: Color = Success400,
            success300: Color = Success300,
            success200: Color = Success200,
            success100: Color = Success100,
            onSuccess: Color = OnSuccess,
            white500: Color = White500,
            white400: Color = White400,
            white300: Color = White300,
            white200: Color = White200,
            white100: Color = White100,
            white: Color = White,
            black: Color = Black,
            black500: Color = Black500,
            black400: Color = Black400,
            black300: Color = Black300,
            black200: Color = Black200,
            black100: Color = Black100,
            darkOpacity500: Color = DarkOpacity500,
            darkOpacity400: Color = DarkOpacity400,
            darkOpacity300: Color = DarkOpacity300,
            darkOpacity200: Color = DarkOpacity200,
            darkOpacity100: Color = DarkOpacity100,
            lightOpacity500: Color = LightOpacity500,
            lightOpacity400: Color = LightOpacity400,
            lightOpacity300: Color = LightOpacity300,
            lightOpacity200: Color = LightOpacity200,
            lightOpacity100: Color = LightOpacity100,
            celestialBlue500: Color = CelestialBlue500,
            celestialBlue400: Color = CelestialBlue400,
            celestialBlue300: Color = CelestialBlue300,
            celestialBlue200: Color = CelestialBlue200,
            celestialBlue100: Color = CelestialBlue100,
        ): DekTekColors = DekTekColors(
            primary = primary,
            secondary = secondary,
            background = background,
            surface = surface,
            onPrimary = onPrimary,
            onSecondary = onSecondary,
            onBackground = onBackground,
            onSurface = onSurface,
            error500 = error500,
            error400 = error400,
            error300 = error300,
            error200 = error200,
            error100 = error100,
            onError = onError,
            success500 = success500,
            success400 = success400,
            success300 = success300,
            success200 = success200,
            success100 = success100,
            onSuccess = onSuccess,
            white500 = white500,
            white400 = white400,
            white300 = white300,
            white200 = white200,
            white100 = white100,
            white = white,
            black = black,
            black500 = black500,
            black400 = black400,
            black300 = black300,
            black200 = black200,
            black100 = black100,
            darkOpacity500 = darkOpacity500,
            darkOpacity400 = darkOpacity400,
            darkOpacity300 = darkOpacity300,
            darkOpacity200 = darkOpacity200,
            darkOpacity100 = darkOpacity100,
            lightOpacity500 = lightOpacity500,
            lightOpacity400 = lightOpacity400,
            lightOpacity300 = lightOpacity300,
            lightOpacity200 = lightOpacity200,
            lightOpacity100 = lightOpacity100,
            celestialBlue500 = celestialBlue500,
            celestialBlue400 = celestialBlue400,
            celestialBlue300 = celestialBlue300,
            celestialBlue200 = celestialBlue200,
            celestialBlue100 = celestialBlue100,
            isDark = true
        )
    }
}
