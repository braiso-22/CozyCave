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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.absoluteValue

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

@Preview
@Composable
private fun DatePickerTextFieldPreview() {
    DatePickerTextField()
}