package com.braiso_22.cozycave.date_time_selector.presentation.date_selector.comps

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.braiso_22.cozycave.R
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialogWrapper(
    isVisible: Boolean,
    setVisible: (Boolean) -> Unit,
    state: Long,
    setState: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = state
    )

    if (isVisible) {
        DatePickerDialog(
            onDismissRequest = { setVisible(false) },
            confirmButton = {
                TextButton(
                    onClick = {
                        setVisible(false)
                        setState(datePickerState.selectedDateMillis?.absoluteValue ?: 0)
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
        ) {
            DatePicker(state = datePickerState)
        }
    }
}
