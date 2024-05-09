package com.ilesha23.habittracker.ui.settingsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ilesha23.habittracker.R

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit = {}
) {
    SettingsScreenContent(
        onBackClick = {
            onBackClick()
        }
    )
}

@Composable
fun SettingsScreenContent(
    onBackClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.fillMaxHeight(0.05f))

        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.08f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {
                    onBackClick()
                },
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1.2f / 1f)
                    .clip(RoundedCornerShape(30))
                    .background(
                        color = MaterialTheme.colorScheme.primary
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = null,
                    modifier = Modifier
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.9f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
            ) {
                Text(
                    text = stringResource(id = R.string.settings_screen_button_privacy).uppercase(),
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .padding(vertical = dimensionResource(id = R.dimen.settings_screen_button_padding))
                )
            }

            Spacer(modifier = Modifier.fillMaxHeight(0.05f))

            Button(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
            ) {
                Text(
                    text = stringResource(id = R.string.settings_screen_button_contact).uppercase(),
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .padding(vertical = dimensionResource(id = R.dimen.settings_screen_button_padding))
                )
            }

            Spacer(modifier = Modifier.fillMaxHeight(0.1f))

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
            ) {
                Image(painter = painterResource(id = R.drawable.logo), contentDescription = null)
            }
        }

    }
}