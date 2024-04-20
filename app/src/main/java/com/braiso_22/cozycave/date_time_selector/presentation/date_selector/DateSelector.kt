package com.braiso_22.cozycave.date_time_selector.presentation.date_selector

import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.braiso_22.cozycave.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.absoluteValue

@Composable
fun DateSelector(
    state: String,
    setState: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val date = remember {
        mutableStateOf(
            try {
                LocalDate.parse(state)
            } catch (e: Exception) {
                LocalDate.now()
            }.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy")
            )
        )
    }

    DateSelectorComponent(
        date = date.value,
        setDate = {
            date.value = it
            setState(date.value)
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DateSelectorComponent(
    date: String,
    setDate: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isDateOpen by remember {
        mutableStateOf(false)
    }
    val datePickerState = rememberDatePickerState()

    if (isDateOpen) {
        DatePickerDialog(
            onDismissRequest = { isDateOpen = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        isDateOpen = false
                        setDate(datePickerState.getDateInString())
                    }
                ) {
                    Text(stringResource(R.string.accept))
                }

            },
            dismissButton = {
                TextButton(onClick = { isDateOpen = false }) {
                    Text(stringResource(R.string.cancel))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
    Text(
        text = date,
        modifier = modifier.clickable {
            isDateOpen = true
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
private fun DatePickerState.getDateInString(): String {
    val valueInMillis = selectedDateMillis?.absoluteValue ?: 0
    val value = valueInMillis.div(86_400_000).toString()
    return try {
        LocalDate.parse(value).toString()
    } catch (e: Exception) {
        ""
    }
}

@Preview
@Composable
private fun DateSelectorPreview() {
    DateSelector(
        state = "",
        setState = {}
    )
}