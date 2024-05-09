package com.ilesha23.habittracker.ui.splashScreen

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.ilesha23.habittracker.R

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    onOnboardingNavigate: () -> Unit = {},
    onMainNavigate: () -> Unit = {}
) {
    val isUserAccepted = viewModel.isUserAccepted.collectAsState().value

    LaunchedEffect(isUserAccepted) {
        if (isUserAccepted != null) {
            if (isUserAccepted) {
                onMainNavigate()
            } else {
                onOnboardingNavigate()
            }
        }
    }
    SplashScreenContent()
}

@Composable
fun SplashScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.background
                    )
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .fillMaxHeight(0.35f)
                .fillMaxWidth(0.8f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (SDK_INT >= 28) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(R.drawable.spalsh_gif)
                        .decoderFactory(ImageDecoderDecoder.Factory())
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            } else {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(R.drawable.spalsh_gif)
                        .decoderFactory(GifDecoder.Factory())
                        .build(),
                    contentDescription = null,
                    modifier = Modifier,
                    contentScale = ContentScale.Crop
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.splash_screen_hello),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }

    }
}