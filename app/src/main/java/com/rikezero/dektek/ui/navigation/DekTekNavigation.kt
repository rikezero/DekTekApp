package com.rikezero.dektek.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rikezero.dektek.ui.collections.CardCollectionsListScreen
import com.rikezero.dektek.ui.navigation.DekTekDestinations.COLLECTIONS_LIST

@Composable
fun DekTekNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = COLLECTIONS_LIST.name
    ) {
        composable(COLLECTIONS_LIST.name) { CardCollectionsListScreen(navController) }
    }
}

enum class DekTekDestinations {
    HOME,
    COLLECTIONS_LIST,
    COLLECTION,
    DECK_LIST,
    DECK
}