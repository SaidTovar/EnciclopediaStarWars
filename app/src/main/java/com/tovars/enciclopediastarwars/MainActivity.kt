package com.tovars.enciclopediastarwars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RawRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.startup.AppInitializer
import app.rive.runtime.kotlin.RiveAnimationView
import app.rive.runtime.kotlin.RiveInitializer
import app.rive.runtime.kotlin.core.ExperimentalAssetLoader
import app.rive.runtime.kotlin.core.Fit
import app.rive.runtime.kotlin.core.Rive
import com.tovars.enciclopediastarwars.ui.theme.EnciclopediaStarWarsTheme
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAssetLoader::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        AppInitializer.getInstance(applicationContext)
            .initializeComponent(RiveInitializer::class.java)
        Rive.init(applicationContext)

        setContent {
            EnciclopediaStarWarsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box (
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xff571717)),
                        contentAlignment = Alignment.Center

                    ){

                        val hazeState = remember { HazeState() }

                        ComposableRiveAnimationView(
                            animation = R.raw.artboard,
                            fit = app.rive.runtime.kotlin.core.Fit.FILL,
                            modifier = Modifier.haze(hazeState)
                        ) {

                        }

                        Box (
                            modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0x2c00437c))
                                .hazeChild(hazeState)
                        ){

                        }

                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalAssetLoader::class)
@Composable
fun ComposableRiveAnimationView(
    modifier: Modifier = Modifier,
    @RawRes animation: Int,
    stateMachineName: String? = null,
    alignment: app.rive.runtime.kotlin.core.Alignment = app.rive.runtime.kotlin.core.Alignment.CENTER,
    fit: Fit = Fit.CONTAIN,
    onInit: (RiveAnimationView) -> Unit
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            RiveAnimationView(context).also {
                it.setRiveResource(
                    resId = animation,
                    stateMachineName = stateMachineName,
                    alignment = alignment,
                    fit = fit,

                    )
            }
        },
        update = { view -> onInit(view) }
    )
}
