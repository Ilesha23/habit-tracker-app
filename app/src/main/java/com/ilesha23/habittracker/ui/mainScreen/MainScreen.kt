package com.ilesha23.habittracker.ui.mainScreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.ilesha23.habittracker.R
import com.ilesha23.habittracker.data.model.HabitItem
import com.ilesha23.habittracker.ui.theme.Blue
import com.ilesha23.habittracker.ui.theme.abeezeeFontFamily
import com.ilesha23.habittracker.ui.theme.actorFontFamily

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onSettingsClick: () -> Unit = {},
    onBacClick: () -> Unit = {}
) {
    var shouldShowDialog by rememberSaveable { mutableStateOf(false) }
    val startDateFormatted = viewModel.dateStartFormatted.collectAsState().value
    val finishDateFormatted = viewModel.dateFinishFormatted.collectAsState().value
    val startDate = viewModel.dateStart.collectAsState().value
    val finishDate = viewModel.dateFinish.collectAsState().value
    val activeList = viewModel.activeList.collectAsState().value
    val archiveList = viewModel.archiveList.collectAsState().value

    BackHandler {
        onBacClick()
    }

    if (shouldShowDialog) {
        CustomDialog(
            dateStartFormatted = startDateFormatted,
            dateFinishFormatted = finishDateFormatted,
            startDate = startDate,
            finishDate = finishDate,
            onStartDateSubmit = {
                viewModel.updateDateStart(it)
            },
            onFinishDateSubmit = {
                viewModel.updateDateFinish(it)
            },
            onDismissRequest = {
                shouldShowDialog = false
            },
            onAcceptClick = { isPositive, name, start, finish ->
                viewModel.insert(
                    type = isPositive,
                    name = name,
                    dateStart = start,
                    dateFinish = finish
                )
                shouldShowDialog = false
            }
        )
    }
    MainScreenContent(
        activeList = activeList,
        archiveList = archiveList,
        onSettingsClick = {
            onSettingsClick()
        },
        onDialogClick = {
            shouldShowDialog = true
        }
    )
}

@Composable
fun MainScreenContent(
    activeList: List<HabitItem>,
    archiveList: List<HabitItem>,
    onSettingsClick: () -> Unit = {},
    onDialogClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.background
            ),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.97f)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.09f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        onSettingsClick()
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
                        onDialogClick()
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {

                Text(
                    text = stringResource(id = R.string.main_screen_active_habits).uppercase(),
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.main_screen_header_padding_start))
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_screen_spacer_height)))

                for (i in activeList) {
                    HabitItem(i)
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_screen_list_items_spacer_height)))
                }

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_screen_spacer_height)))

                Text(
                    text = stringResource(id = R.string.main_screen_archive_habits).uppercase(),
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.main_screen_header_padding_start))
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_screen_spacer_height)))

                for (i in archiveList) {
                    HabitItem(i)
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_screen_list_items_spacer_height)))
                }

            }

        }

    }
}

@Composable
fun HabitItem(
    item: HabitItem
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color =
        if (item.isArchive) MaterialTheme.colorScheme.tertiaryContainer
        else if (item.isPositive) MaterialTheme.colorScheme.primaryContainer
        else MaterialTheme.colorScheme.secondaryContainer,
        shape = RoundedCornerShape(30),
        border =
        if (item.isArchive) BorderStroke(2.dp, MaterialTheme.colorScheme.tertiary)
        else if (item.isPositive) BorderStroke(2.dp, MaterialTheme.colorScheme.primaryContainer)
        else BorderStroke(2.dp, MaterialTheme.colorScheme.secondaryContainer)
    ) {

        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.main_screen_item_card_padding))
        ) {

            Text(
                text = item.name,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.main_screen_item_card_text_start_padding))
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
                        text = item.formattedStartedDate,
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
                        text = item.daysPassed.toString(),
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
                        text = item.formattedCutoffDate,
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CustomDialog(
    dateStartFormatted: String,
    dateFinishFormatted: String,
    startDate: Long,
    finishDate: Long,
    onStartDateSubmit: (Long) -> Unit = {},
    onFinishDateSubmit: (Long) -> Unit = {},
    onDismissRequest: () -> Unit = {},
    onAcceptClick: (Boolean, String, Long, Long) -> Unit = { _, _, _, _ -> }
) {
    var isPositive by remember { mutableStateOf(true) }
    var text by remember { mutableStateOf("") }
    var showDateStartDialog by remember { mutableStateOf(false) }
    var showDateFinishDialog by remember { mutableStateOf(false) }

    if (showDateStartDialog) {
        DatePickerDialog(
            currentDate = startDate,
            onSubmitDate = {
                onStartDateSubmit(it)
            },
            onDismissRequest = {
                showDateStartDialog = false
            }
        )
    }

    if (showDateFinishDialog) {
        DatePickerDialog(
            currentDate = finishDate,
            onSubmitDate = {
                onFinishDateSubmit(it)
            },
            onDismissRequest = {
                showDateFinishDialog = false
            }
        )
    }

    Popup(
        alignment = Alignment.Center,
        onDismissRequest = {
            onDismissRequest()
        },
        properties = PopupProperties(
            focusable = true
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.onTertiary
                ),
            contentAlignment =
                if (WindowInsets.isImeVisible) {
                    Alignment.TopCenter
                } else Alignment.Center
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {

                Surface(
                    shape = RoundedCornerShape(15),
                    color = MaterialTheme.colorScheme.background,
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.fillMaxHeight(0.03f))

                        Text(
                            text = stringResource(id = R.string.main_screen_dialog_header).uppercase(),
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )

                        Spacer(modifier = Modifier.fillMaxHeight(0.03f))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(
                                onClick = {
                                    isPositive = true
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer
                                ),
                                shape = RoundedCornerShape(45),
                                border =
                                if (isPositive) BorderStroke(
                                    2.dp,
                                    MaterialTheme.colorScheme.primaryContainer
                                )
                                else BorderStroke(0.dp, Color.Transparent)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.main_screen_dialog_positive).uppercase(),
                                    style = MaterialTheme.typography.headlineSmall,
                                    modifier = Modifier
                                        .padding(
                                            vertical = dimensionResource(id = R.dimen.main_screen_popup_button_type_padding_vertical),
                                            horizontal = dimensionResource(id = R.dimen.main_screen_popup_button_type_padding_horizontal)
                                        )
                                )
                            }
                            Button(
                                onClick = {
                                    isPositive = false
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                                ),
                                shape = RoundedCornerShape(45),
                                border =
                                if (!isPositive) BorderStroke(
                                    2.dp,
                                    MaterialTheme.colorScheme.secondaryContainer
                                )
                                else BorderStroke(0.dp, Color.Transparent)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.main_screen_dialog_negative).uppercase(),
                                    style = MaterialTheme.typography.headlineSmall,
                                    modifier = Modifier
                                        .padding(
                                            vertical = dimensionResource(id = R.dimen.main_screen_popup_button_type_padding_vertical),
                                            horizontal = dimensionResource(id = R.dimen.main_screen_popup_button_type_padding_horizontal)
                                        )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.fillMaxHeight(0.04f))

                        Text(
                            text = stringResource(id = R.string.main_screen_dialog_habit_name).uppercase(),
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )

                        Spacer(modifier = Modifier.fillMaxHeight(0.03f))

                        BasicTextField(
                            value = text,
                            onValueChange = {
                                text = it
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .drawBehind {
                                    val strokeWidth = 2
                                    val y = size.height - strokeWidth / 2
                                    drawLine(
                                        color = Blue,
                                        start = Offset(0f, y),
                                        end = Offset(size.width, y),
                                        strokeWidth = 4f
                                    )
                                },
                            textStyle = MaterialTheme.typography.headlineSmall.copy(
                                color = MaterialTheme.colorScheme.primary
                            ),
                        )

                        Spacer(modifier = Modifier.fillMaxHeight(0.03f))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = stringResource(id = R.string.main_screen_dialog_start).uppercase(),
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Spacer(modifier = Modifier.fillMaxHeight(0.03f))
                                Text(
                                    text = dateStartFormatted,
                                    style = MaterialTheme.typography.headlineSmall,
                                    color = MaterialTheme.colorScheme.primary,
                                    textDecoration = TextDecoration.Underline,
                                    modifier = Modifier
                                        .clickable {
                                            showDateStartDialog = true
                                        }
                                )
                            }

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = stringResource(id = R.string.main_screen_dialog_finish).uppercase(),
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Spacer(modifier = Modifier.fillMaxHeight(0.03f))
                                Text(
                                    text = dateFinishFormatted,
                                    style = MaterialTheme.typography.headlineSmall,
                                    color = MaterialTheme.colorScheme.primary,
                                    textDecoration = TextDecoration.Underline,
                                    modifier = Modifier
                                        .clickable {
                                            showDateFinishDialog = true
                                        }
                                )
                            }

                        }

                        Spacer(modifier = Modifier.fillMaxHeight(0.06f))

                    }

                }

                Spacer(modifier = Modifier.fillMaxHeight(0.1f))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            onDismissRequest()
                        },
                        modifier = Modifier
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.background
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.main_screen_dialog_cancel).uppercase(),
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .padding(vertical = dimensionResource(id = R.dimen.main_screen_popup_button_padding_vertical))
                        )
                    }

                    Spacer(modifier = Modifier.weight(0.1f))

                    Button(
                        onClick = {
                            onAcceptClick(
                                isPositive,
                                text,
                                startDate,
                                finishDate
                            )
                        },
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = stringResource(id = R.string.onboarding_screen_button_accept).uppercase(),
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.background,
                            modifier = Modifier
                                .padding(vertical = dimensionResource(id = R.dimen.main_screen_popup_button_padding_vertical))
                        )
                    }
                }

            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    currentDate: Long,
    onDismissRequest: () -> Unit,
    onSubmitDate: (Long) -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
            val datePickerState =
                rememberDatePickerState(initialSelectedDateMillis = currentDate)
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    headlineContentColor = MaterialTheme.colorScheme.primary,
                    weekdayContentColor = MaterialTheme.colorScheme.primary,
                    dayContentColor = MaterialTheme.colorScheme.primary,
                    selectedDayContentColor = MaterialTheme.colorScheme.background,
                    navigationContentColor = MaterialTheme.colorScheme.primary,
                )
            )
            Row {
                TextButton(
                    onClick = {
                        onDismissRequest()
                        onSubmitDate(datePickerState.selectedDateMillis ?: 0)
                    }
                ) {
                    Text(
                        text = "Ok",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                TextButton(
                    onClick = onDismissRequest
                ) {
                    Text(
                        text = "Cancel",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}