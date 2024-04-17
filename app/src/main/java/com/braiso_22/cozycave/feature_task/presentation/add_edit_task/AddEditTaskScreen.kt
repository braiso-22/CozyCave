package com.braiso_22.cozycave.feature_task.presentation.add_edit_task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.braiso_22.cozycave.R
import com.braiso_22.cozycave.feature_task.presentation.add_edit_task.state.AddEditTaskUiState
import kotlinx.coroutines.flow.collectLatest
import com.braiso_22.cozycave.feature_task.presentation.add_edit_task.AddEditTaskViewModel.UiEvent

@Composable
fun AddEditTaskScreen(
    onBack: () -> Unit,
    onUiMessage: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddEditTaskViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    LaunchedEffect(key1 = Unit) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                is UiEvent.GoBack -> {
                    onBack()
                }

                is UiEvent.ShowSnackbar -> onUiMessage(it.message)
            }
        }
    }

    AddEditTask(
        modifier = modifier,
        state = state,
        setName = {
            viewModel.onEvent(
                AddEditTaskEvent.EnteredName(it)
            )
        },
        setDescription = {
            viewModel.onEvent(
                AddEditTaskEvent.EnteredDescription(it)
            )
        },
        onSave = {
            viewModel.onEvent(
                AddEditTaskEvent.SaveTask
            )
        },
        onBack = onBack,
    )
}

@Preview(showBackground = true)
@Composable
fun AddEditTaskPreview() {
    var state = remember { AddEditTaskUiState() }
    AddEditTask(
        state = state,
        setName = { state = state.copy(name = it) },
        setDescription = { state = state.copy(description = it) },
        onBack = {},
        onSave = {},
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTask(
    state: AddEditTaskUiState,
    setName: (String) -> Unit,
    setDescription: (String) -> Unit,
    onBack: () -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.add_new_task)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
            )
        },
    ) {
        Column(
            modifier = Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DataTextFieldColum(
                state = state,
                setName = { setName(it) },
                setDescription = { setDescription(it) },
                modifier = Modifier.fillMaxWidth(),
            )
            Button(
                onClick = onSave,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
            ) {
                Text(text = "Save")
            }
        }
    }
}


@Composable
fun DataTextFieldColum(
    modifier: Modifier = Modifier,
    state: AddEditTaskUiState,
    setName: (String) -> Unit,
    setDescription: (String) -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.name,
            onValueChange = {
                setName(it)
            },
            label = {
                Text(text = "Name")
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.description,
            onValueChange = { setDescription(it) },
            label = {
                Text(text = "Description")
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        )
    }
}

@Composable
fun enabledStyles(): TextFieldColors {
    return OutlinedTextFieldDefaults.colors(
        disabledTextColor = MaterialTheme.colorScheme.onSurface,
        disabledBorderColor = MaterialTheme.colorScheme.outline,
        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
        // For Icons
        disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
    )
}
