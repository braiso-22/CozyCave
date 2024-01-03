package com.braiso_22.cozycave.feature_task.ui.add_edit_task

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.braiso_22.cozycave.feature_task.ui.add_edit_task.components.TimePickerDialog
import com.braiso_22.cozycave.feature_task.ui.add_edit_task.components.selectedTime
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.math.absoluteValue

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun AddEditTaskScreen(
    modifier: Modifier = Modifier,
    viewModel: AddEditTaskViewModel = hiltViewModel()
) {
    var state = viewModel.state.value

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
            setDate = {
                viewModel.onEvent(
                    AddEditTaskEvent.EnteredDate(it)
                )
            },
            setTime = {
                viewModel.onEvent(
                    AddEditTaskEvent.EnteredTime(it)
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
    var state = remember { AddEditTaskState() }
    AddEditTask(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        state = state,
        setName = { state = state.copy(name = it) },
        setDescription = { state = state.copy(description = it) },
        setDate = { state = state.copy(date = it) },
        setTime = { state = state.copy(time = it) },
    )
}

@Composable
fun AddEditTask(
    modifier: Modifier = Modifier,
    state: AddEditTaskState,
    setName: (String) -> Unit,
    setDescription: (String) -> Unit,
    setDate: (LocalDate?) -> Unit,
    setTime: (LocalTime?) -> Unit,
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
            setDate = { setDate(it) },
            setTime = { setTime(it) },
        )
        ButtonsRow(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            onConfirm = {
                onSave()
            }
        )
    }
}

@Composable
fun DataTextFieldColum(
    modifier: Modifier = Modifier,
    state: AddEditTaskState,
    setName: (String) -> Unit = {},
    setDescription: (String) -> Unit = {},
    setDate: (LocalDate?) -> Unit = {},
    setTime: (LocalTime?) -> Unit = {},
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
        DatePickerTextField(
            modifier = Modifier
                .padding(8.dp),
            state = state.date,
            setState = { setDate(it) }
        )
        TimePickerTextField(
            modifier = Modifier
                .padding(8.dp),
            state = state.time,
            setState = { setTime(it) }
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerTextField(
    modifier: Modifier = Modifier,
    state: LocalDate? = remember { null },
    setState: (LocalDate?) -> Unit = {}
) {
    var openDateDialog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = state?.toEpochDay()
    )
    OutlinedTextField(
        enabled = false,
        modifier = modifier.clickable {
            openDateDialog = true
        },
        colors = enabledStyles(),
        value = state?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: "",
        onValueChange = { },
        label = {
            Text(text = "Initial date")
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Date picker icon",
                modifier = Modifier
                    .padding(8.dp)
                    .height(24.dp)
            )
        },
    )
    if (openDateDialog) {
        DatePickerDialog(
            onDismissRequest = { openDateDialog = false },
            confirmButton = {
                Button(onClick = {
                    setState(
                        if (datePickerState.selectedDateMillis != null) {
                            LocalDate.ofEpochDay(
                                datePickerState.selectedDateMillis!!.absoluteValue / 86400000
                            )
                        } else {
                            null
                        }
                    )
                    openDateDialog = false
                }) {
                    Text(text = "Save")
                }
            },
            dismissButton = {
                Button(onClick = { openDateDialog = false }) {
                    Text(text = "Cancel")
                }
            },

            ) {
            DatePicker(state = datePickerState)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerTextField(
    modifier: Modifier = Modifier,
    state: LocalTime? = remember { null },
    setState: (LocalTime?) -> Unit = {}
) {
    val timePickerState: TimePickerState = rememberTimePickerState(
        is24Hour = true,
        initialHour = state?.hour ?: LocalTime.now().hour,
        initialMinute = state?.minute ?: LocalTime.now().minute
    )
    var openTimeDialog by remember { mutableStateOf(false) }
    OutlinedTextField(
        enabled = false,
        modifier = modifier
            .clickable {
                openTimeDialog = true
            },
        colors = enabledStyles(),
        value = state?.toString() ?: "",
        onValueChange = { },
        label = {
            Text(text = "Initial time")
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Date picker icon",
                modifier = Modifier
                    .padding(8.dp)
                    .height(24.dp)
            )
        },
    )
    if (openTimeDialog) {
        TimePickerDialog(
            title = "Time for the task",
            onDismissRequest = { openTimeDialog = false },
            confirmButton = {
                Button(onClick = {
                    val timePickerMilis: Long = timePickerState.selectedTime()
                    setState(
                        LocalTime.ofSecondOfDay(timePickerMilis)
                    )
                    openTimeDialog = false
                }) {
                    Text(text = "Save")
                }
            },
            dismissButton = {
                Button(onClick = { openTimeDialog = false }) {
                    Text(text = "Cancel")
                }
            },
        ) {
            TimePicker(state = timePickerState)
        }
    }
}

@Composable
fun ButtonsRow(
    modifier: Modifier = Modifier,
    onConfirm: () -> Unit = {},
    onCancel: () -> Unit = {}
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
private fun enabledStyles(): TextFieldColors {
    return OutlinedTextFieldDefaults.colors(
        disabledTextColor = MaterialTheme.colorScheme.onSurface,
        disabledBorderColor = MaterialTheme.colorScheme.outline,
        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
        //For Icons
        disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
    )
}
