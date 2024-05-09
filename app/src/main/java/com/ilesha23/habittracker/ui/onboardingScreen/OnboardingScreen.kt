package com.ilesha23.habittracker.ui.onboardingScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.ilesha23.habittracker.R

@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel = hiltViewModel(),
    onNavigate: () -> Unit = {}
) {
    OnboardingScreenContent(
        onAcceptClick = {
            viewModel.setUserAccepted()
            onNavigate()
        }
    )
}

@Composable
fun OnboardingScreenContent(
    onAcceptClick: () -> Unit = {}
) {
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
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
        ) {
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = null)
        }

        Spacer(modifier = Modifier.fillMaxHeight(0.05f))

        Text(
            text = stringResource(id = R.string.onboarding_screen_welcome),
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.fillMaxHeight(0.05f))

        Text(
            text = stringResource(id = R.string.onboarding_screen_info),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.inversePrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.fillMaxHeight(0.13f))

        Button(
            onClick = {
                onAcceptClick()
            }
        ) {
            Text(
                text = stringResource(id = R.string.onboarding_screen_button_accept).uppercase(),
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .padding(vertical = dimensionResource(id = R.dimen.onboarding_screen_button_vertical_padding))
                    .padding(horizontal = dimensionResource(id = R.dimen.onboarding_screen_button_horizontal_padding))
            )
        }
    }
}