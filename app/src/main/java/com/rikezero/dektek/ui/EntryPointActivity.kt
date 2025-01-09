package com.rikezero.dektek.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.rikezero.dektek.ui.foundation.theme.DekTekTheme
import com.rikezero.dektek.ui.navigation.DekTekNavigation

class EntryPointActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            DekTekTheme {
                DekTekNavigation()
            }
        }
    }
}