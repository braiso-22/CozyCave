package com.braiso_22.cozycave.feature_task.presentation.add_edit_task.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.braiso_22.cozycave.feature_task.presentation.add_edit_task.enabledStyles
import java.time.LocalTime
import java.time.format.DateTimeParseException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerTextField(
    modifier: Modifier = Modifier,
    state: String = remember { "" },
    setState: (String) -> Unit = {},
) {
    val localTimeState = remember {
        try {
            LocalTime.parse(state)
        } catch (e: DateTimeParseException) {
            LocalTime.now()
        }
    }
    val timePickerState: TimePickerState = rememberTimePickerState(
        is24Hour = true,
        initialHour = localTimeState.hour  ,
        initialMinute = localTimeState.minute
    )
    var openTimeDialog by remember { mutableStateOf(false) }
    OutlinedTextField(
        enabled = false,
        modifier = modifier
            .clickable {
                openTimeDialog = true
            },
        colors = enabledStyles(),
        value = state,
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
                        LocalTime.ofSecondOfDay(timePickerMilis).toString()
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

@Preview
@Composable
private fun TimePickerTextFieldPreview() {
    TimePickerTextField()
}