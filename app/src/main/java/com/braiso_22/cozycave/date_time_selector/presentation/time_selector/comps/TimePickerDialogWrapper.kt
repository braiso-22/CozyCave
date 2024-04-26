package com.braiso_22.cozycave.date_time_selector.presentation.time_selector.comps

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.braiso_22.cozycave.R
import com.braiso_22.cozycave.date_time_selector.presentation.time_selector.TimeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialogWrapper(
    isVisible: Boolean,
    setVisible: (Boolean) -> Unit,
    state: TimeState,
    setState: (TimeState) -> Unit,
    modifier: Modifier = Modifier,
) {
    val timePickerState = rememberTimePickerState(
        is24Hour = true,
        initialMinute = state.minute,
        initialHour = state.hour,
    )
    if (isVisible) {
        TimePickerDialog(
            state = timePickerState,
            onDismissRequest = { setVisible(false) },
            confirmButton = {
                TextButton(
                    onClick = {
                        setVisible(false)
                        setState(
                            TimeState(timePickerState.hour, timePickerState.minute)
                        )
                    }
                ) {
                    Text(stringResource(R.string.accept))
                }
            },
            dismissButton = {
                TextButton(onClick = { setVisible(false) }) {
                    Text(stringResource(R.string.cancel))
                }
            },
            modifier = modifier
        )
    }
}
