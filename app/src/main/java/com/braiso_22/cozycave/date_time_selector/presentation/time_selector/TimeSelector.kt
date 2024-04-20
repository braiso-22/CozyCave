package com.braiso_22.cozycave.date_time_selector.presentation.time_selector

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.braiso_22.cozycave.R
import com.braiso_22.cozycave.date_time_selector.presentation.components.TimePickerDialog
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@Composable
fun TimeSelector(
    state: String,
    setState: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val time = remember {
        mutableStateOf(
            try {
                LocalTime.parse(state)
            } catch (e: Exception) {
                LocalTime.now()
            }.format(
                DateTimeFormatter.ofPattern("HH:mm")
            )
        )
    }

    DateTimeSelectorContent(
        time = time.value,
        setTime = {
            time.value = it
            setState(time.value)
        },
        modifier = modifier
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DateTimeSelectorContent(
    time: String,
    setTime: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isTimeOpen by remember {
        mutableStateOf(false)
    }
    val timePickerState = rememberTimePickerState(
        is24Hour = true
    )
    if (isTimeOpen) {
        TimePickerDialog(
            state = timePickerState,
            onDismissRequest = { isTimeOpen = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        isTimeOpen = false
                        setTime("${timePickerState.hour}:${timePickerState.minute}")
                    }
                ) {
                    Text(stringResource(R.string.accept))
                }
            },
            dismissButton = {
                TextButton(onClick = { isTimeOpen = false }) {
                    Text(stringResource(R.string.cancel))
                }
            }
        )
    }

    Text(
        text = time,
        modifier = modifier.clickable {
            isTimeOpen = true
        }
    )
}

@Preview
@Composable
private fun DateTimeSelectorPreview() {
    Surface {
        TimeSelector(
            state = "",
            setState = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )
    }
}