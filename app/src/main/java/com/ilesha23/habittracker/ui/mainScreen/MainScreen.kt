package com.ilesha23.habittracker.ui.mainScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ilesha23.habittracker.R
import com.ilesha23.habittracker.ui.theme.abeezeeFontFamily
import com.ilesha23.habittracker.ui.theme.actorFontFamily

@Composable
fun MainScreen() {
    MainScreenContent()
}

@Composable
fun MainScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.background
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.95f)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.09f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {

                    },
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1.2f / 1f)
                        .clip(RoundedCornerShape(30))
                        .background(
                            color = MaterialTheme.colorScheme.secondary
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = null,
                        modifier = Modifier
                    )
                }
                IconButton(
                    onClick = {

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
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = null,
                        modifier = Modifier
                    )
                }
            }

            Spacer(modifier = Modifier.fillMaxHeight(0.03f))

            LazyColumn(
                modifier = Modifier
            ) {

                item {
                    Text(
                        text = stringResource(id = R.string.main_screen_active_habits).uppercase(),
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(start = dimensionResource(id = R.dimen.main_screen_header_padding_start))
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                }

                items(10) {
                    HabitItem()
                    Spacer(modifier = Modifier.height(15.dp))
                }

                item {
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = stringResource(id = R.string.main_screen_archive_habits).uppercase(),
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(start = dimensionResource(id = R.dimen.main_screen_header_padding_start))
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                }

                items(10) {
                    Spacer(modifier = Modifier.height(12.dp))
                    HabitItem()
                }

            }

        }

    }
}

@Composable
fun HabitItem(
    name: String = "Drink water every day",
    dateStarted: String = "01\nJAN\n2000",
    havePassed: String = "6\ndays",
    dateCutOff: String = "10\nMAR\n2001",
    isGood: Boolean = true,
    isArchive: Boolean = false
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color =
        if (isArchive) MaterialTheme.colorScheme.tertiaryContainer
        else if (isGood) MaterialTheme.colorScheme.primaryContainer
        else MaterialTheme.colorScheme.secondaryContainer,
        shape = RoundedCornerShape(30),
        border =
        if (isArchive) BorderStroke(2.dp, MaterialTheme.colorScheme.tertiary)
        else if (isGood) BorderStroke(2.dp, MaterialTheme.colorScheme.primaryContainer)
        else BorderStroke(2.dp, MaterialTheme.colorScheme.secondaryContainer)
    ) {

        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {

            Text(
                text = name,
                style = MaterialTheme.typography.headlineLarge
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.main_screen_started),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.inversePrimary,
                        fontFamily = actorFontFamily
                    )
                    Spacer(modifier = Modifier.fillMaxHeight(0.01f))
                    Text(
                        text = dateStarted,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontFamily = abeezeeFontFamily,
                        textAlign = TextAlign.Center
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.main_screen_passed),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.inversePrimary,
                        fontFamily = actorFontFamily
                    )
                    Spacer(modifier = Modifier.fillMaxHeight(0.01f))
                    Text(
                        text = havePassed,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontFamily = abeezeeFontFamily,
                        textAlign = TextAlign.Center
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.main_screen_cutoff),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.inversePrimary,
                        fontFamily = actorFontFamily
                    )
                    Spacer(modifier = Modifier.fillMaxHeight(0.01f))
                    Text(
                        text = dateCutOff,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontFamily = abeezeeFontFamily,
                        textAlign = TextAlign.Center
                    )
                }

            }

        }

    }
}