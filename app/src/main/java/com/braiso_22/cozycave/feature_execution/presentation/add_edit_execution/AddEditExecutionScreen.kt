package com.braiso_22.cozycave.feature_execution.presentation.add_edit_execution

import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.WatchLater
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.braiso_22.cozycave.R
import com.braiso_22.cozycave.feature_execution.presentation.add_edit_execution.components.DateTimeRow
import com.braiso_22.cozycave.feature_execution.presentation.add_edit_execution.state.AddEditExecutionUiState

@Composable
fun AddEditExecutionScreen(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    viewModel: AddEditExecutionViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    val snackbarHostState = remember { SnackbarHostState() }
    AddEditExecutionScreenContent(
        state = state,
        setState = {
            viewModel.onEvent(
                AddEditExecutionEvent.ChangeState(it)
            )
        },
        windowSizeClass = windowSizeClass,
        snackbarHostState = snackbarHostState,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditExecutionScreenContent(
    state: AddEditExecutionUiState,
    setState: (AddEditExecutionUiState) -> Unit,
    snackbarHostState: SnackbarHostState,
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Tasks") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            var alreadyCompleted by remember {
                mutableStateOf(false)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { alreadyCompleted = !alreadyCompleted }
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(imageVector = Icons.Outlined.WatchLater, "")
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text("Already completed")
                }
                Switch(
                    checked = alreadyCompleted,
                    onCheckedChange = { alreadyCompleted = !alreadyCompleted }
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
            DateTimeRow(
                date = state.startDate,
                setDate = {
                    setState(
                        state.copy(
                            startDate = it
                        )
                    )
                },
                time = state.startTime,
                setTime = {
                    setState(
                        state.copy(
                            startTime = it
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            if (alreadyCompleted) {
                Spacer(modifier = Modifier.padding(8.dp))
                DateTimeRow(
                    date = state.startDate,
                    setDate = {
                        setState(
                            state.copy(
                                startDate = it
                            )
                        )
                    },
                    time = state.startTime,
                    setTime = {
                        setState(
                            state.copy(
                                startTime = it
                            )
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.save))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AddEditExecutionScreenContentPreview(dpSize: DpSize) {
    val state = remember {
        mutableStateOf(
            AddEditExecutionUiState(),
        )
    }
    AddEditExecutionScreenContent(
        state = state.value,
        setState = {},
        windowSizeClass = WindowSizeClass.calculateFromSize(dpSize),
        snackbarHostState = SnackbarHostState(),
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showBackground = true, widthDp = 360, heightDp = 720)
@Composable
fun AddEditExecutionScreenContentVerticalPreview() {
    AddEditExecutionScreenContentPreview(DpSize(360.dp, 720.dp))
}

@Preview(showBackground = true, widthDp = 720, heightDp = 360)
@Composable
fun AddEditExecutionScreenContentHorizontalPreview() {
    AddEditExecutionScreenContentPreview(DpSize(720.dp, 360.dp))
}