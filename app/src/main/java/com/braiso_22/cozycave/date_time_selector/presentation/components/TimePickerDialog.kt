package com.braiso_22.cozycave.date_time_selector.presentation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Keyboard
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    state: TimePickerState,
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    dismissButton: @Composable (() -> Unit)? = null,
) {
    var isTimePicker by remember {
        mutableStateOf(true)
    }

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        )
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = MaterialTheme.colorScheme.surface
                )
        ) {
            Column(modifier.padding(8.dp)) {
                Text("Select a time", Modifier.padding(8.dp))
                if (isTimePicker) {
                    TimePicker(state = state)
                } else {
                    TimeInput(state = state)
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { isTimePicker = !isTimePicker }) {
                        Icon(
                            imageVector = Icons.Outlined.Keyboard,
                            "Change to write mode"
                        )
                    }
                    if (dismissButton != null) {
                        dismissButton()
                    }
                    confirmButton()
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewScreenSizes
@Composable
private fun TimePickerPreview() {
    Box(Modifier.fillMaxSize()) {
        TimePickerDialog(
            state = rememberTimePickerState(),
            onDismissRequest = {},
            confirmButton = {
                TextButton(onClick = { /*TODO*/ }) {
                    Text("Accept")
                }
            },
        )
    }
}