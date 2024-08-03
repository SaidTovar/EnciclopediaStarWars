package com.tovars.enciclopediastarwars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.startup.AppInitializer
import app.rive.runtime.kotlin.RiveInitializer
import app.rive.runtime.kotlin.core.Rive
import com.tovars.enciclopediastarwars.presentation.home.HomeScreem
import com.tovars.enciclopediastarwars.ui.theme.EnciclopediaStarWarsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        AppInitializer.getInstance(applicationContext)
            .initializeComponent(RiveInitializer::class.java)
        Rive.init(applicationContext)

        setContent {
            EnciclopediaStarWarsTheme {

                HomeScreem()

            }
        }
    }
}



