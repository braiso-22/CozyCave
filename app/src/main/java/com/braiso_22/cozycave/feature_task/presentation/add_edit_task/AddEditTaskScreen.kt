package com.braiso_22.cozycave.feature_task.presentation.add_edit_task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.braiso_22.cozycave.feature_task.presentation.add_edit_task.components.TimePickerTextField
import com.braiso_22.cozycave.feature_task.presentation.add_edit_task.state.AddEditTaskUiState

@Composable
fun AddEditTaskScreen(
    modifier: Modifier = Modifier,
    viewModel: AddEditTaskViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    Column(modifier) {
        Row {
            Text(text = "Add/Edit task")
        }
        AddEditTask(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
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
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddEditTaskPreview() {
    var state = remember { AddEditTaskUiState() }
    AddEditTask(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        state = state,
        setName = { state = state.copy(name = it) },
        setDescription = { state = state.copy(description = it) },
    )
}

@Composable
fun AddEditTask(
    modifier: Modifier = Modifier,
    state: AddEditTaskUiState,
    setName: (String) -> Unit,
    setDescription: (String) -> Unit,
    onSave: () -> Unit = {},
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DataTextFieldColum(
            modifier = Modifier.fillMaxWidth(),
            state = state,
            setName = { setName(it) },
            setDescription = { setDescription(it) },
        )
    }

    ButtonsRow(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),
        onConfirm = {
            onSave()
        }
    )
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
            modifier = Modifier.padding(8.dp),
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
            modifier = Modifier.padding(8.dp),
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
fun ButtonsRow(
    modifier: Modifier = Modifier,
    onConfirm: () -> Unit = {},
    onCancel: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(onClick = { onCancel() }) {
            Text(text = "Cancel")
        }
        Button(onClick = { onConfirm() }) {
            Text(text = "Save")
        }
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
